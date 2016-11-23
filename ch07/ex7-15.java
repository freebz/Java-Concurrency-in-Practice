// 예제 7.15  LogWriter에 추가한 안정적인 종료 방법

public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private fianl PrintWriter writer;
    @GuardedBy("this") private boolean isShutdown;
    @GuardedBy("this") private int reservations;

    public void start() { loggerThread.start(); }

    public void stop() {
	synchronized (this) { isShutdown = true; }
	loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
	synchronized (this) {
	    if (isShutdown)
		throw new IllegalStateException(...);
	    ++reservations;
	}
	queue.put(msg);
    }

    private class LoggerThread extends Thread {
	public void run() {
	    try {
		while (true) {
		    try {
			synchronized (LogService.this) {
			    if (isShutdown && reservations == 0)
				break;
			}
			String msg = queue.take();
			synchronized (LogService.this) {
			    --reservations;
			}
			writer.println(msg);
		    } catch (InterruptedException e) { /* 재시도 */ }
		}
	    } finally {
		writer.close();
	    }
	}
    }
}
