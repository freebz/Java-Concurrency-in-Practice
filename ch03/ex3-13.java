// 예제 3.13  최신 값을 불변 객체에 넣어 volatile 변수에 보관

@ThreadSafe
public class VolatileCachedFactorizer implements Servlet {
    private volatile OneValueCache cache =
	new OneValueCache (null, null);

    public void service(ServletRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = cache.getFactors(i);

	if (factors == null) {
	    factors = factor(i);
	    cache = new OneValueCache(i, factors);
	}
	encodeIntoResponse(resp, factors);
    }
}
