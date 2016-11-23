// 예제 2.5  단일 연산을 적절히 사용하지 못한 상태에서 결과 값을 캐시하려는 서블릿. 이런 코드는 금물!

@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber
	= new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors
	= new AtomicReference<BigInteger[]>();
    
    public void service(ServiceRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	if (i.equals(lastNumber.get()))
	    encodeIntoResponse(resp, lastFactors.get());
	else {
	    BigInteger[] factors = factors(i);
	    lastNumber.set(i);
	    lastFactors.set(factors);
	    encodeIntoResponse(resp, factors);
	}
    }
}
