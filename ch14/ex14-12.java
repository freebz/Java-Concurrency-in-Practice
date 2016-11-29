// 예제 14.12  Lock을 사용해 구현한 카운팅 세마포어

// java.util.concurrent.Semaphore 클래스가 실제로 이렇게 구현되어 있지는 않다.
@ThreadSafe
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    // 조건 서술어: permitsAvailable (permits > 0)
    private final Condition permitsAvailable = lock.newCondition();
    @GuardedBy("lock") private int permits;

    SemaphoreOnLock (int initialPermits ) {
	lock.lock();
	try {
	    permits = initialPermits;
	} finally {
	    lock.unlock();
	}
    }

    // 만족할 때까지 대기: permitsAvailable
    public void acquire() throws InterruptedException {
	lock.lock();
	try {
	    while (permits <= 0)
		permitsAvailable.await();
	    --permits;
	} finally {
	    lock.unlock();
	}
    }

    public void release() {
	lock.lock();
	try {
	    ++permits;
	    permitsAvailable.signal();
	} finally {
	    lock.unlock();
	}
    }
}
