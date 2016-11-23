// 예제 7.21  종료된 이후에도 실행이 중단된 작업이 어떤 것인지 알려주는 ExecutorService

public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<RUnnable> tasksCancelledAtShutdown =
	Collections.synchronizedSet(new HashSet<Runnable>());
    //...
    public List<Runnable> getCancelledTasks() {
	if (!exec.isTerminated())
	    throw new IllegalStateException(...);
	return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    public void execute(final Runnable runnable) {
	exec.execute(new Runnable() {
	    public void run() {
		try {
		    runnable.run();
		} finally {
		    if (isShutdown()
			&& Thread.currentThread().isInterrupted())
			tasksCancelledAtShutdown.add(runnable);
		}
	    }
	});
    }

    // ExecutorService의 다른 메소드는 모두 exec에게 위임
}

			
