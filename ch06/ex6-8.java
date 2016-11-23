// 예제 6.8  종료 기능을 추가한 웹서버

class LifecycleWebServer {
    private static final ExecutorService exec = ...;

    public void start() throws IOException {
	ServerSocket socket = new ServerSocket(80);
	while (!exec.isShutdown()) {
	    try {
		final Socket conn = socket.accept();
		exec.execute(new Runnable() {
		    public void run() { handleRequest(conn); }
		});
	    } catch (RejectedExecutionException e) {
		if (!exec.isShutdown())
		    log("task submission rejected", e);
	    }
	}
    }

    public void stop() { exec.shutdown(); }

    void handleRequest(Socket connection) {
	Request req = readRequest(connection);
	if (isShutdownRequest(req))
	    stop();
	else
	    dispatchRequest(req);
    }
}
