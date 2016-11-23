// 예제 8.2  ThreadPoolExecutor의 범용 생성 메소드

public ThreadPoolExecutor (int corePoolSize,
			   int maximumPoolSize,
			   long keepAliveTime,
			   TimeUnit unit,
			   BlockingQueue<Runnable> workQueue,
			   ThreadFactory threadFactory,
			   RejectedExecutionHandler handler) { ... }
