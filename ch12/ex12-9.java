// 예제 12.9  스레드 풀의 스레드 개수가 제대로 늘어나는지를 확인할 수 있는 테스트 케이스

public void testPoolExpansion() throws InterruptedException {
    int MAX_SIZE = 10;
    TestingThreadFactory threadFactory = new TestingThreadFactory();
    ExecutorService exec
	= Executors.newFixedThreadPool(MAX_SIZE, threadFactory);

    for (int i = 0; i < 10 * MAX_SIZE; i++)
	exec.execute(new Runnable() {
	    public void run() {
		try {
		    Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		}
	    }
	});
    for (int i = 0;
	 i < 20 && threadFactory.numCreated.get() < MAX_SIZE;
	 i++)
	Thread.sleep(100);
    assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
    exec.shutdownNow();
}
