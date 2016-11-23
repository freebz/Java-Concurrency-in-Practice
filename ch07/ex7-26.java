// 예제 7.26  로그 서비스를 종료하는 종료 훅을 등록

public void start() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
	public void run() {
	    try { LogService.this.stop(); }
	    catch (InterruptedException ignored) {}
	}
    });
}
