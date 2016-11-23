// 예제 7.19  IndexingService의 컨슈머 스레드

public class IndexerThread extends Thread {
    public void run() {
	try {
	    while (true) {
		File file = queue.task();
		if (file == POISON)
		    break;
		else
		    indexFile(file);
	    }
	} catch (InterruptedException consumed) { }
    }
}
