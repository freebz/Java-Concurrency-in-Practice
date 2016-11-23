// 예제 7.10  Future를 사용해 작업 중단하기

public static void timedRun(Runnable r,
			    long timeout, TimeUnit unit)
                            throws InterruptedException {
    Future<?> task = taskExec.submit(r);
    try {
	task.get(timeout, unit);
    } catch (TimeoutException e) {
	// finally 블록에서 작업이 중단될 것이다.
    } catch (ExecutionException e) {
	// 작업 내부에서 예외 상황 발생. 예외를 다시 던진다.
	throw launderThrowable(e.getCause());
    } finally {
	// 이미 종료했다 하더라도 별다른 악영향은 없다.
	task.cancel(true);  // 실행중이라면 인터럽트를 건다.
    }
}
