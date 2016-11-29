// 예제 14.5  세련되지 못한 대기 방법을 사용하는 SleepyBoundedBuffer

@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public SleepyBoundedBuffer(int size) { super(size); }

    public void put(V v) throws InterruptedException {
	while (true) {
	    synchronized (this) {
		if (!isFull()) {
		    doPut(v);
		    return;
		}
	    }
	    Thread.sleep(SLEEP_GRANULARITY);
	}
    }

    public V take() throws InterruptedException {
	while (true) {
	    synchronized (this) {
		if (!isEmpty())
		    return doTake();
	    }
	    Thread.sleep(SLEEP_GRANULARITY);
	}
    }
}
