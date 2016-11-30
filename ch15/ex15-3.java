// 예제 15.3  CAS를 사용해 다중 변수의 안전성을 보장하는 예

public class CasNumberRange {
    @Immutable
    private static class IntPair {
	final int lower; // 불변조건: lover <= upper
	final int upper;
	...
    }
    private final AtomicReference<IntPair> values =
	new AtomicReference<IntPair>(new IntPair(0, 0));

    public int getLower() { return values.get().lower; }
    public int getUpper() { return values.get().upper; }

    public void setLower(int i) {
	while (true) {
	    IntPair oldv = values.get();
	    if (i > oldv.upper)
		throw new IllegalArgumentException(
		    "Can't set lower to " + i + " > upper");
	    IntPair newv = new IntPair(i, oldv.upper);
	    if (values.compareAndSet(oldv, newv))
		return;
	}
    }
    // setUpper 메소드도 setLover와 비슷하다.
}
