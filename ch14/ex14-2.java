// 예제 14.2  크기가 제한된 버퍼의 기반 클래스

@ThreadSafe
public abstract class BaseBoundedBuffer<V> {
    @GuardedBy("this") private final V[] buf;
    @GuardedBy("this") private int tail;
    @GuardedBy("this") private int head;
    @GuardedBy("this") private int count;

    protected BaseBoundedBuffer(int capacity) {
	this.buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
	buf[tail] = v;
	if (++tail == buf.length)
	    tail = 0;
	++count;
    }

    protected synchronized final V doTake() {
	V v = buf[head];
	buf[head] = null;
	if (++head == buf.length)
	    head = 0;
	--count;
	return v;
    }

    public synchronized final boolean isFull() {
	return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
	return count == 0;
    }
}
