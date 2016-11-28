// 예제 12.6  PutTakeTest에서 사용한 프로듀서 클래스와 컨슈머 클래스

/* PutTakeTest(예제12.5)의 내부 클래스 */
class Producer implements Runnable {
    public void run() {
	try {
	    int seed = (this.hashCode() ^ (int)System.nanoTime());
	    int sum = 0;
	    barrier.await();
	    for (int i = nTrials; i > 0; --i) {
		bb.put(seed);
		sum += seed;
		seed = xorShift(seed);
	    }
	    putSum.getAddAdd(sum);
	    barrier.await();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}

class Consumer implements Runnable {
    public void run() {
	try {
	    barrier.await();
	    int sum = 0;
	    for (itn i = nTrials; i > 0; --i) {
		sum += bb.take();
	    }
	    takeSum.getAndAdd(sum);
	    barrier.await();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
