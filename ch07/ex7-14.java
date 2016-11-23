// 예제 7.14  로그 서비스에 종료 기능을 덧붙이지만 안정적이지 않은 방법

public void log(String msg) throws InterruptedException {
    if (!shutdownRequested)
	queue.put(msg);
    else
	throw new IllegalStateException("logger is shut down");
}
