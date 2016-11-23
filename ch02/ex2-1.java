// 예제 2.1  상태 없는 서블릿

@ThreadSafe
public class StatelessFactorizer implements Servlet {
    public void service(ServletRequest req, ServletResponse resp) {
	BigInteger i = extractFromRequest(req);
	BigInteger[] factors = factor(i);
	encodeIntoResponse(resp, factors);
    }
}
