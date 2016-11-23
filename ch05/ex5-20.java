// 예제 5.20  Memoizer를 사용해 결과를 캐시하는 인수분해 서블릿

@ThreadSafe
public class Factorizer implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c =
	new Computable<BigInteger, BigInteger[]>() {
	    public BigInteger[] compute(BigInteger arg) {
		return factor(arg);
	    }
	};
    private final Computable<BigInteger, BigInteger[]> cache
	= new Memoizer<BigInteger, BigInteger[]>(c);

    public void service(ServletRequest req,
			ServletResponse resp) {
	try {
	    BigInteger i = extractFromRequest(req);
	    encodeIntoResponse(resp, cache.compute(i));
	} catch (InterruptedException e) {
	    encodeError(resp, "factorization interrupted");
	}
    }
}
