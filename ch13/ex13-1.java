// 예제 13.1  Lock 인터페이스

public interface Lock {
    void lock();
    void lockInterruptibly() throws InterruptedException;
    boolean tryLock();
    boolean tryLock(long timeout, TimeUnit unit)
	throws InterruptedException;
    void unlock();
    Condition newCondition();
}
