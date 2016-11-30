// 예제 15.2  CAS 기반으로 구현한 넌블로킹 카운터 클래스

@ThreadSafe
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
	return value.get();
    }

    public int increment() {
	int v;
	do {
	    v = value.get();
	}
	while (v != value.compareAndSwap(v, v + 1));
	return v + 1;
    }
}
