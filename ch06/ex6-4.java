// 예제 6.4  스레드 풀을 사용한 웹서버

class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor exec
	= Executors.newFixedThreadPool(NTHREADS);
    
    public static void main(String[] args) throws IOException {
	ServerSocket socket = new ServerSocket(80);
	while (true) {
	    final Socket connection = socket.accept();
	    Runnable task = new Runnable() {
		public void run() {
		    handleRequest(connection);
		}
	    };
	    exec.execute(task);
	}
    }
}
