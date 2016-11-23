// 예제 7.1  volatile 변수를 사용해 취소 상태를 확인

@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes
	= new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    public void run() {
	BigInteger p = BigInteger.ONE;
	while (!cancelled) {
	    p = p.nextProbablePrime ();
	    synchronized (this) {
		primes.add(p);
	    }
	}
    }

    public void cancel() { cancelled = true; }

    public synchronized List<BigInteger> get() {
	return new ArrayList<BigInteger>(primes);
    }
}
