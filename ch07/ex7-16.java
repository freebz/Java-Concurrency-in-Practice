// 예제 7.16  ExecutorService를 활용한 로그 서비스

public class LogService {
    private final ExecutorSErvice exec = new SingleThreadExecutor();
    //...
    public void start() { }

    public void stop() throws InterruptedException {
	try {
	    exec.shutdown();
	    exec.awaitTermination(TIMEOUT, UNIT);
	} finally {
	    writer.close();
	}
    }

    public void log(String msg) {
	try {
	    exec.execute(new WriteTask(msg));
	} catch (RejectedExecutionException ignored) { }
    }
}
