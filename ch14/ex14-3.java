// 예제 14.3  선행 조건이 맞지 않으면 그냥 멈춰버리는 버퍼 클래스

@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer(int size) { super(size); }

    public synchronized void put(V v) throws BufferFullException {
	if (isFull())
	    throw new BufferFullException();
	doPut(v);
    }

    public synchronized V take() throws BufferEmptyExcepiton {
	if (isEmpty())
	    throw new BufferEmptyExcepiton();
	return doTake();
    }
}
