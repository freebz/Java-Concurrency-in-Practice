// 예제 7.5  인터럽트를 사용해 작업을 취소

public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
	this.queue = queue;
    }

    public void run() {
	try {
	    BigInteger p = BigInteger.ONE;
	    while (!Thread.currentThread().isInterrupted())
		queue.put(p = p.nextProbablePrime());
	} catch (InterruptedException consumed) {
	    /* 스레드를 종료한다. */
	}
    }
    public void cancel() { cancelled = true; }
}
