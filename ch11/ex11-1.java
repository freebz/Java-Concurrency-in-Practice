// 예제 11.1  작업 큐에 대한 순차적인 접근

public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
	this.queue = queue;
    }

    public void run() {
	while (true) {
	    try {
		Runnable task = queue.take();
		task.run();
	    } catch (InterruptedException e) {
		break; /* 스레드를 종료시킨다 */
	    }
	}
    }
}
