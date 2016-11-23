// 예제 8.1  단일 스레드 Executor에서 데드락이 발생하는 작업 구조, 이런 코드는 금물!

public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
	public String call() throws Exception {
	    Future<String> header, footer;
	    header = exec.submit(new LoadFileTask("header.html"));
	    footer = exec.submit(new LoadFileTask("footer.html"));
	    String page = renderBody();
	    // 데드락 발생
	    return header.get() + page + footer.get();
	}
    }
}
