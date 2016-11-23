// 예제 4.15  클라이언트 측 락을 사용해 putIfAbsent 메소드를 구현

@ThreadSafe
public class ListHelper<E> {
    public List<E> list =
	Collections.synchronizedList(new ArrayList<E>());
    // ...
    public boolean putIfAbsent(E x) {
	synchronized (list) {
	    boolean absent = !list.contains(x);
	    if (absent)
		list.add(x);
	    return absent;
	}
    }
}
