// 예제 6.12  ThreadPoolExecutor.newTaskFor 메소드의 기본 구현 내용

protected <T> RunnableFuture<T> newTaskFor(Callable<T> task) {
    return new FutureTask<T>(task);
}
