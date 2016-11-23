// 예제 7.13  종료 기능이 구현되지 않은 프로듀서-컨슈머 패턴의 로그 서비스

public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;

    public LogWriter(Writer writer) {
	this.queue = new LinkedBlockingQueue<String>(CAPACITY);
	this.logger = new LoggerThread(writer);
    }

    public void start() { logger.start(); }

    public void log(String msg) throws InterruptedException {
	queue.put(msg);
    }

    private class LoggerThread extends Thread {
	private final PrintWriter writer;
	//...
	public void run() {
	    try {
		while (true)
		    writer.println(queue.take());
	    } catch(InterruptedException ignored) {
	    } finally {
		writer.close();
	    }
	}
    }
}
