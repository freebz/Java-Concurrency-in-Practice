// 예제 5.11  CountDownLatch를 사용해 스레드의 실행과 종료를 확인해 전체 실행 시간을 확인한다

public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task)
	    throws InterruptedException {
	final CountDownLatch startGate = new CountDownLatch(1);
	final CountDownLatch endGate = new CountDownLatch(nThreads);

	for (int i = 0; i < nThreads; i++) {
	    Thread t = new Thread() {
		    public void run() {
			try {
			    startGate.await();
			    try {
				task.run();
			    } finally {
				endGate.countDown();
			    }
			} catch (InterruptedException ignored) { }
		    }
		};
	    t.start();
	}

	long start = System.nanoTime();
	startGate.countDown();
	endGate.await();
	long end = System.nanoTime();
	return end-start;
    }
}
