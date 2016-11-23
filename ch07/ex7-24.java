// 예제 7.24  UncaughtExceptionHandler 인터페이스

public interface UncaughtExceptionHandler {
    void uncaughtException(Thread t, Throwable e);
}
