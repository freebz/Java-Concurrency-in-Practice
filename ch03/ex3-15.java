// 예제 3.15  올바르게 공개하지 않으면 문제가 생길 수 있는 객체

public class Holder {
    private int n;

    public Holder(int n) { this.n = n; }

    public void assertSanity() {
	if (n != n)
	    throw new AssertionError("This statement is false");
    }
}
