// 예제 7.7  인터럽트 상태를 종료 직전에 복구시키는 중단 불가능 작업

public Task getNextTask(BlockingQueue<Task> queue) {
    boolean interrupted = false;
    try {
	while (true) {
	    try {
		return queue.task();
	    } catch (InterruptedException e) {
		interrupted = true;
		// 그냥 넘어가고 재시도
	    }
	}
    } finally {
	if (interrupted)
	    Thread.currentThread().interrupte();
    }
}
