// 예제 14.14  AbstractQueuedSynchronizer를 활용한 바이너리 래치 클래스

@ThreadSafe
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() { sync.releaseShared(0); }

    public void await() throws InterruptedException {
	sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
	protected int tryAcquireShared(int ignored) {
	    // 래치가 열려 있는 상태(state==1)라면 성공, 아니면 실패
	    return (getState() == 1) ? 1 : -1;
	}

	protected boolean tryReleaseShared(int ignored) {
	    setState(1); // 래치가 열렸다
	    return true; // 다른 스레드에서 확보 연산에 성공할 가능성이 있다
	}
    }
}
