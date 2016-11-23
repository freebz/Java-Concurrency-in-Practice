// 예제 6.13  Future를 사용해 이미지 파일 다운로드 작업을 기다림

public class FutureRenderer {
    private final ExecutorService executor = ...;
    
    void renderPage(CharSequence source) {
	final List<ImageInfo> imageInfos = scanForImageInfo(source);
	Callable<List<ImageData>> task =
	    new Callable<List<ImageData>>() {
		public List<ImageData> call() {
		    List<ImageData> result
		            = new ArrayList<ImageData>();
		    for (ImageInfo imageInfo : imageInfos)
			result.add(imageInfo.downloadImage());
		    return result;
		}
	    };

	Future<List<ImageData>> future = executor.submit(task);
	renderText(source);
	try {
	    List<ImageData> imageData = future.get();
	    for (ImageData data : imageData)
		renderImage(data);
	} catch (InterruptedException e) {
	    // 스레드의 인터럽트 상태를 재설정
	    Thread.currentThread().interrupt();
	    // 결과는 더 이상 필요없으니 해당 작업도 취소한다.
	    future.cancel(true);
	} catch (ExecutionException e) {
	    throw launderThrowable(e.getCause());
	}
    }
}
