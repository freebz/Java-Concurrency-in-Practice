// 예제 16.2  동기화 피기백 방법을 사용하고 있는 FutureTask의 내부 클래스

// FutureTask의 내부 클래스
private final class Sync extends AbstractQueuedSynchronizer {
    private static final int RUNNING = 1, RUN = 2, CANCELLED = 4;
    private V result;
    private Exception exception;

    void innserSet(V v) {
	while (true) {
	    int s = getState();
	    if (ranOrCancelled(s))
		return;
	    if (compareAndSetState (s, RAN))
		break;
	}
	result = v;
	releaseShared(0);
	done();
    }

    V innerGet() throws InterruptedException, ExecutionException {
	acquireSharedInterruptibly(0);
	if (getState() == CANCELLED)
	    throw new CancellationException();
	if (exception != null)
	    throw new ExecutionException(exception);
	return result;
    }
}
