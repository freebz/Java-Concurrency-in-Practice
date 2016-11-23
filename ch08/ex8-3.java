// 예제 8.3  스레드의 개수와 작업 큐의 크기가 제한된 스레드 풀을 만들면서 호출자 실행 정책을 지정하는 모습

ThreadPoolExecutor executor
    = new ThreadPoolExecutor(N_THREADS, N_THREADS,
         0L, TimeUnit.MILLISECONDS,
	 new LinkedBlockingQueue<Runnable>(CAPACITY));
executor.setRejectedExecutionHandler(
    new ThreadPoolExecutor.CallerRunsPolicy());
