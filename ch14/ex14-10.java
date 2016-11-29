// 예제 14.10  Condition 인터페이스

public interface Condition {
    void await() throws InterruptedException ;
    boolean await(long time, TimeUnit unit)
	    throws InterruptedException ;
    long awaitNanos(long nanosTimeout) throws InterruptedException;
    void awaitUninterruptibly();
    boolean awaitUntil(Date deadline) throws InterruptedException;

    void signal();
    void signalAll();
}
