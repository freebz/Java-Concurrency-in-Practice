// 예제 12.5  BoundedBuffer를 테스트하는 프로듀서-컨슈머 구조의 테스트 프로그램

public class PutTakeTest {
    private static final ExecutorService pool
	= Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    private final int nTrials, nPairs;

    public static void main(String[] args) {
	new PutTakeTest(10, 10, 100000).test(); // 예제 인자 값
	pool.shutdown();
    }

    PutTakeTest(int capacity, int npairs, int ntraials) {
	this.bb = new BoundedBuffer<Integer>(capacity);
	this.nTrials = ntrials;
	this.nPairs = npairs;
	this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test() {
	try {
	    for (int i = 0; i < nPairs; i++) {
		pool.execute(new Producer());
		pool.execute(new Consumer());
	    }
	    barrier.await(); // 모든 스레드가 준비될 때까지 대기
	    barrier.await(); // 모든 스레드의 작업이 끝날 때까지 대기
	    assertEquals(putSum.get(), takeSum.get());
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    class Producer implements Runnable { /* 예제 12.6 */ }

    class Consumer implements Runnable { /* 예제 12.6 */ }
}
