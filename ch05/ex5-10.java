// 예제 5.10  인터럽트가 발생했음을 저장해 인터럽트 상황을 잊지 않도록 한다

public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;
    //...
    public void run() {
	try {
	    processTask(queue.task());
	} catch (InterruptedException e) {
	    // 인터럽트가 발생한 사실을 저장한다
	    Thread.currentThread().interrupt();
	}
    }
}
