// 예제 8.5  ThreadFactory 인터페이스

public interface ThreadFactory {
    Thread newThread(Runnable r);
}
