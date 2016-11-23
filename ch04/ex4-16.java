// 예제 4.16  재구성 기법으로 putIfAbsent 메소드 구현

@ThreadSafe
public class ImprovedList<T> implements List<T> {
    private final List<T> list;

    public ImprovedList(List<T> list) { this.list = list; }
    
    public synchronized boolean putIfAbsent(T x) {
	boolean contains = list.contains(x);
	if (!contains)
	    list.add(x);
	return !contatins;
    }

    public synchronized void clear() { list.clear(); }
    // ... List 클래스의 다른 메소드도 clear와 비슷하게 구현
}
