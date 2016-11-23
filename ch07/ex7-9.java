// 예제 7.9  작업 실행 전용 스레드에 인터럽트 거는 방법

public static void timedRun(Runnable r,
			    long timeout, TimeUnit unit)
                            throws InterruptedException {
    class RethrowableTask implements Runnable {
	private volatile Throwable t;
	public void run() {
	    try { r.run(); }
	    catch (Throwable t) { this.t = t; }
	}
	void rethrow() {
	    if (t != null)
		throw launderThrowable(t);
	}
    }

    RethrowableTask task = new RethrowableTask();
    final Thread taskThread = new Thread(task);
    taskThread.start();
    cancelExec.schedule(new Runnable() {
        public void run() { taskThread.interrupt(); }
    }, timeout, unit);
    taskThread.join(unit.toMillis(timeout));
    task.rethrow();
}
