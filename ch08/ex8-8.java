// 예제 8.8  기본 팩토리 메소드로 만들어진 Executor의 설정 변경 모습

ExecutorService exec = Executors.newCachedThreadPool();
if (exec instanceof ThreadPoolExecutor)
    ((ThreadPoolExecutor ) exec).setCorePoolSize(10);
else
    throw new AssertionError("Oops, bad assumption");
