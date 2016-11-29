// 예제 14.11  명시적인 조건 큐를 활용해 작성한 크기가 제한된 버퍼

@ThreadSafe
public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();

    // 조건 서술어: notFull (count < items.length)
    private final Condition notFull = lock.newCondition();
    // 조건 서술어: notEmpty (count > 0)
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    @GuardedBy("lock") private int tail, head, count;

    // 만족할 때까지 대기: notFull
    public void put(T x) throws InterruptedException {
	lock.lock();
	try {
	    while (count == items.length)
		notFull.await();
	    items[tail] = x;
	    if (++tail == items.length)
		tail = 0;
	    ++count;
	    notEmpty.signal();
	} finally {
	    lock.unlock();
	}
    }

    // 만족할 때까지 대기: notEmpty
    public T take() throws InterruptedException {
	lock.lock();
	try {
	    while (count == 0)
		notEmpty.await();
	    T x = items[head];
	    items[head] = null;
	    if (++head == items.length)
		head = 0;
	    --count;
	    notFull.signal();
	    return x;
	} finally {
	    lock.unlock();
	}
    }
}
