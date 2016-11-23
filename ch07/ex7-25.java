// 예제 7.25  예외 내용을 로그 파일에 출력하는 UncaughtExceptionHandler

public class UEHLogger implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
	Logger logger = Logger.getAnonymousLogger();
	logger.log(Level.SEVERE,
		   "Thread terminated with exception: " + t.getName(), e);
    }
}
