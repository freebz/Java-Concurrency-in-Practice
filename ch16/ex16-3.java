// 예제 16.3  안전하지 않은 늦은 초기화. 이런 코드는 금물!

@NotThreadSafe
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
	if (resource == null)
	    resource = new Resource(); // 안전하지 않은 공개
	return resource;
    }
}
