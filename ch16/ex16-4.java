// 예제 16.4  스레드 안전한 초기화 방법

@ThreadSafe
public class SafeLazyInitialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
	if (resource == null)
	    resource = new Resource();
	return resource;
    }
}
