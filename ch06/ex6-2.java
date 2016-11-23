// 예제 6.2  요청이 들어올 때마다 스레드를 생성하는 웹서버

class ThreadPerTeskWebServer {
    public static void main(String[] args) throws IOException {
	ServerSocket socket = new ServerSocket(80);
	while (true) {
	    final Socket connection = socket.accept();
	    Runnable task = new Runnable() {
		    public void run() {
			handleRequest(connection);
		    }
		};
	    new Thread(task).start();
	}
    }
}
