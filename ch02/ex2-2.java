// 예제 2.2  동기화 구문 없이 요청 횟수를 세는 서블릿, 이런 코드는 금물!

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;

    public long getCount() { return count; }
    
    public void service(ServletRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = factor(i);
	++count;
	encodeIntoResponse(resp, factors);
    }
}
