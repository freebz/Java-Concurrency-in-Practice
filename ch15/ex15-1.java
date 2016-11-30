// 예제 15.1  CAS 연산을 그대로 구현한 코드

@ThreadSafe
public class SimulatedCAS {
    @GuardedBy("this") private int value;

    public synchronized int get() { return value; }

    public synchronized int compareAndSwap(int expectedValue ,
					   int newValue) {
	int oldValue = value;
	if (oldValue == expectedValue)
	    value = newValue;
	return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue ,
					      int newValue) {
	return (expectedValue
		== compareAndSwap(expectedValue, newValue));
    }
}
