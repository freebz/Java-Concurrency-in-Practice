// 예제 6.1  순차적으로 처리하는 웹서버

class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
	ServerSocket socket = new ServerSocket(80);
	while (true) {
	    Socket connection = socket.accept();
	    handleRequest(connection);
	}
    }
}
