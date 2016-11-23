// 예제 7.23  스레드 풀에서 사용하는 작업용 스레드의 일반적인 모습

public void run() {
    Throwable thrown = null;
    try {
	while (!isInterrupted())
	    runTask(getTaskFromWorkQueue());
    } catch (Throwable e) {
	thrown = e;
    } finally {
	threadExited(this, thrown);
    }
}
