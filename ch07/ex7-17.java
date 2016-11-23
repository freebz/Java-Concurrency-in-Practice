// 예제 7.17  독약 객체를 사용해 서비스를 종료

public class IndexingService {
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    class CrawlerThread extends Thread { /* 예제 7.18 */ }
    class IndexerThread extends Thread { /* 예제 7.19 */ }

    public void start() {
	producer.start();
	consumer.start();
    }

    public void stop() { producer.interrupt(); }

    public void awaitTermination() throws InterruptedException {
	consumer.join();
    }
}
