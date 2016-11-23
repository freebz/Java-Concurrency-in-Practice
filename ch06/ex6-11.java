// 예제 6.11  Callable과 Future 인터페이스

public interface Callable<V> {
    V call() throws Exception;
}

public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    V get() throws InterruptedException, ExecutionException,
		   CancellationException;
    V get(long timeout, TimeUnit unit)
	throws InterruptedException, ExecutionException,
	       CancellationException, TimeoutException;
}
