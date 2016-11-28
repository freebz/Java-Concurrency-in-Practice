// 예제 13.6  ReadWriteLock 인터페이스

public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}
