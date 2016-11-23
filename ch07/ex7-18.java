// 예제 7.18  IndexingService의 프로듀서 스레드

public class CrawlerThread extends Thread {
    public void run() {
	try {
	    crawl(root);
	} catch (InterruptedException e) { /* 통과 */ }
	finally {
	    while (true) {
		try {
		    queue.put(POISON);
		    break;
		}  catch (InterruptedException e1) { /* 재시도 */ }
	    }
	}
    }

    private void crawl(File root) throws InterruptedException {
	//...
    }
}
