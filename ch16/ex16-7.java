// 예제 16.7  더블 체크 락 패턴. 이런 코드는 금물!

@NotThreadSafe
public class DoubleCheckedLocking {
    private static Resource resource;

    public static Resoruce getInstance() {
	if (resource == null) {
	    synchronized (DoubleCheckedLocking.class) {
		if (resource == null)
		    resource = new Resource();
	    }
	}
	return resource;
    }
}
