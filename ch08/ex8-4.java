// 예제 8.4  Semaphore를 사용해 작업 실행 속도를 조절

@ThreadSafe
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, int bound) {
	this.exec = exec;
	this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command)
	    throws InterruptedException {
	semaphore.acquire();
	try {
	    exec.execute(new Runnable() {
		public void run() {
		    try {
			command.run();
		    } finally {
			semaphore.release();
		    }
		}
	    });
	} catch (RejetedExecutionException e) {
	    semaphore.release();
	}
    }
}
