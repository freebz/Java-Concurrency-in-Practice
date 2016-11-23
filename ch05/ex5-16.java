// 예제 5.16  HashMap과 동기화 기능을 사용해 구현한 첫 번째 캐시

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

public class ExpensiveFunction
        implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
	// 잠시 생각 좀 하고...
	return new BigInteger(arg);
    }
}

public class Memoizer1<A, V> implements Computable<A, V> {
    @Guarded("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
	this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
	V result = cache.get(arg);
	if (result == null) {
	    result = c.compute(arg);
	    cache.put(arg, result);
	}
	return result;
    }
}
