// 예제 12.8  ThreadPoolExecutor를 테스트하기 위한 TestingThreadFactory

class TestingThreadFactory implements ThreadFactory {
    public final AtomicInteger numCreated = new AtomicInteger();
    private final ThreadFactory factory
	= Executors.defaultThreadFactory();

    public Thread newThread(Runnable r) {
	numCreated.incrementAndGet();
	return factory.newThread(r);
    }
}
