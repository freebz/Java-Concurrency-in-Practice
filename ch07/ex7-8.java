// 예제 7.8  임시로 빌려 사용하는 스레드에 인터럽트 거는 방법. 이런 코드는 금물!

private static final ScheduledExecutiorService cancelExec = ...;

public static void timedRun(Runnable r,
			    long timeout, TimeUnit unit) {
    final Thread taskThread = Thread.currentThread();
    cancelExec.schedule(new Runnable() {
        public void run() { taskThread.interrupt(); }
    }, timeout, unit);
    r.run();
}
