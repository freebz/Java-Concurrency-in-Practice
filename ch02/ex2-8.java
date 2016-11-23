// 예제 2.8  최근 입력 값과 그 결과를 캐시하는 서블릿

@ThreadSafe
public class CachedFactorizer implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    public synchronized long getHits() { return hits; }
    public synchronized double getCacheHitRatio() {
	return (double) cacheHits / (double) hits;
    }
    
    public void service(ServiceRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = null;
	synchronized (this) {
	    ++hits;
	    if (i.equals(lastNumber)) {
		++cacheHits;
		factors = lastFactors.clone();
	    }
	}
	if (factors == null) {
	    factors = factors(i);
	    synchronized (this) {
		lastNumber = i;
		lastFactors = factors.clone();
	    }
	}
	encodeIntoResponse(resp, factors);
    }
}
