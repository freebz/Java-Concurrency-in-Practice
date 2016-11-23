// 예제 6.7  ExecutorService 인터페이스의 동작 주기 관리

public interface ExecutorService extends Executor {
    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit)
	throws InterruptedException;
    // ... 작업을 등록할 수 있는 몇 가지 추가 메소드
}
