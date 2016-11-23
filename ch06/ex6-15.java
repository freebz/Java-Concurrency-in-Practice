// 예제 6.15  CompletionService를 사용해 페이지 구성 요소를 받아오는 즉시 렌더링

public class Renderer {
    private final ExecutorService executor;

    Renderer(ExecutionService executor) { this.executor = executor; }
    
    void renderPage(CharSequence source) {
	final List<ImageInfo> info = scanForImageInfo(source);
	CompletionService<ImageData> completionService =
	    new ExecutorCompletionService<ImageData>(executor);
	for (final ImageInfo imageInfo : info)
	    completionService.submit( new Callable<ImageData>() {
		public ImageData call() {
		    return imageInfo.downloadImage();
		}
	    });

	renderText(source);
	
	try {
	    for (int t = 0, n = info.size(); t < n; t++) {
		Future<ImageData> f = completionService.take();
		ImageData imageData = f.get();
		renderImage(imageData);
	    }
	} catch (InterruptedException e) {
	    Thread.currentThread().interrupt();
	} catch (ExecutionException e) {
	    throw launderThrowable(e.getCause());
	}
    }
}
