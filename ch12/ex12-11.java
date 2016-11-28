// 예제 12.11  배리어 기반의 타이머

public class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
	long t = System.nanoTime();
	if (!started) {
	    started = true;
	    startTime = t;
	} else
	    endTime = t;
    }
    public synchronized void clear() {
	started = false;
    }
    public synchronized long getTime() {
	return endTime - startTime;
    }
}
