// 예제 8.6  직접 작성한 스레드 팩토리

public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
	this.poolName = poolName;
    }

    public Thread newThread(Runnabel runnable) {
	return new MyAppThread(runnable, poolName);
    }
}
