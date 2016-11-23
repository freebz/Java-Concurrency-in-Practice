// 예제 7.20  메소드 내부에서 Executor를 사용하는 모습

boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
        throws InterruptedException {
    ExecutorService exec = Executors.newCachedThreadPool();
    final AtomicBoolean hasNewMail = new AtomicBoolean(false);
    try {
	for (final String host : hosts)
	    exec.execute(new Runnable() {
		public void run() {
		    if (checkMail(host))
			hasNewMail.set(true);
		}
	    });
    } finally {
	exec.shutdown();
	exec.awaitTermination(timeout, unit);
    }
    return hasNewMail.get();
}
