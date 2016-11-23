// 예제 6.5  작업마다 스레드를 새로 생성시키는 Executor

public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
	new Thread(r).start();
    };
}
