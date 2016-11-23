// 예제 6.10  페이지 내용을 순차적으로 렌더링

public class SingleThreadRenderer {
    void renderPage(CharSequence source) {
	renderText(source);
	List<ImageData> imageData = new ArrayList<ImageData>();
	for (ImageInfo imageInfo : scanForImageInfo(source))
	    imageData.add(imageInfo.downloadImage());
	for (ImageData data : imageData)
	    renderImage(data);
    }
}
