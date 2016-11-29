// 예제 14.6  조건 큐를 사용해 구현한 BoundedBuffer

@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    // 조건 서술어: not-full (!isFull())
    // 조건 서술어: not-empty (!isEmpty())

    public BoundedBuffer(int size) { super(size); }

    // 만족할 때까지 대기: not-full
    public synchronized void put(V v) throws InterruptedException {
	while (isFull())
	    wait();
	doPut(v);
	notifyAll();
    }

    // 만족할 때까지 대기: not-empty
    public synchronized V take() throws InterruptedException {
	while(isEmpty())
	    wait();
	V v = doTake();
	notifyAll();
	return v;
    }
}
