// 예제 2.4  AtomicLong 객체를 이용해 요청 횟수를 세는 서블릿

@ThreadSafe
public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() { return count.get(); }

    public void service(ServiceRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = factors(i);
	count.incrementAndGet();
	encodeIntoResponse(resp, factors);
    }
}
