// 예제 8.9  ThreadPoolExecutor를 상속받아 로그와 시간 측정 기능을 추가한 클래스

public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime
	= new ThreadLocal<Long>();
    private final Logger log = Logger.getLogger("TimingThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    protected void beforeExecute(Thread t, Runnable r) {
	super.beforeExecute(t, r);
	log.fine(String.format("Thread %s: start %s", t, r));
	startTime.set(System.nanoTime());
    }

    protected void afterExecute(Runnable t, Throwable t) {
	try {
	    long endTime = System.nanoTime();
	    long taskTime = endTime - startTime.get();
	    numTasks.incrementAndGet();
	    totalTime.addAndGet(taskTime);
	    log.fine(String.format("Thread %s: end %s, time=%dns",
				   t, r, taskTime));
	} finally {
	    super.afterExecute(r, t);
	}
    }

    protected void terminated() {
	try {
	    log.info(String.format("Terminated: avg time=%dns",
				   totalTime.get() / numTasks.get()));
	} finally {
	    super.terminated();
	}
    }
}
