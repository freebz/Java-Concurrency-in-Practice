// 예제 4.10  숫자 범위를 나태내는 클래스. 의존성 조건을 정확하게 처리하지 못하고 있다

public class NumberRange {
    // 의존성 조건: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
	// 주의 - 안전하지 않은 비교문
	if (i > upper.get())
	    throw new IllegalArgumentException (
                    "can't set lower to " + i + " > upper");
	lower.set(i);
    }

    public void setUpper(int i) {
	// 주의 - 안전하지 않은 비교문
	if (i < lower.get())
	    throw new IllegalArgumentException (
                    "can't set upper to " + i + " < lower");
	upper.set(i);
    }
    

    public boolean isInRange(int i) {
	return (i >= lower.get() && i <= upper.get());
    }
}
