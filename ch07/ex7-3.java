// 예제 7.3  프로듀서가 대기 중인 상태로 계속 멈춰 있을 가능성이 있는 안전하지 않는 취소 방법의 예. 이런 코드는 금물!

public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
	this.queue = queue;
    }

    public void run() {
	try {
	    BigInteger p = BigInteger.ONE;
	    while (!cancelled)
		queue.put(p = p.nextProbablePrime());
	} catch (InterruptedException consumed) { }
    }

    public void cancel() { cancelled = true; }
}
void consumePrimes() throws InterruptedException {
    BlockingQueue<BigInteger> primes = ...;
    BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
    producer.start();
    try {
	while (needMorePrimes())
	    consume(primes.take());
    } finally {
	producer.cancel();
    }
}
