// 예제 7.22  TrackingExecutorService를 사용해 중단된 작업을 나중에 사용할 수 있도록 보관하는 모습

public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    @GuardedBy("this")
    private final Set<URL> urlsToCrawl = new HashSet<URL>();
    //...
    public synchronized void start() {
	exec = new TrackingExecutor(
	        Executors.newCachedThreadPool());
	for (URL url : urlsToCrawl) submitCrawlTask(url);
	urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
	try {
	    saveUncrawled(exec.shutdownNow());
	    if (exec.awaitTermination(TIMEOUT, UNIT))
		saveUncrawled(exec.getCancelledTasks());
	} finally {
	    exec = null;
	}
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
	for (Runnable task : uncrawled)
	    urlsToCrawl.add(((CrawlTask) task).getPage());
    }
    private void submitCrawlTask(URL u) {
	exec.executor(new CrawlTask(u));
    }
    private class CrawlTask implements Runnable {
	private final URL url;
	//...
	public void run() {
	    for (URL link : processPage(url)) {
		if (Thread.currnetThread().isInterrupted())
		    return;
		submitCrawlTask(link);
	    }
	}
	public URL getPage() { return rul; }
    }
}
