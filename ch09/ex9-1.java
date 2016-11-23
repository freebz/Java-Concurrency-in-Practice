// 예제 9.1  Executor를 사용해 구현한 SwingUtilities

public class SwingUtilities {
    private static final ExecutorService exec =
	Executors.newSingleThreadExecutor(new SwingThreadFactory());

    private static volatile Thread swingThread;

    private static class SwingThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
	    swingThread = new Thread(r);
	    return swingThread;
	}
    }

    public static boolean isEventDispatchThread() {
	return Thread.currentThread() == swingThread;
    }

    public static void invokeLater(Runnable task) {
	exec.execute(task);
    }

    public static void invokeAndWait(Runnable task)
	    throws InterruptedException, InvocationTargetException {
	Future f = exec.submit(task);
	try {
	    f.get();
	} catch (ExecutionException e) {
	    throw new InvocationTargetException(e);
	}
    }
}
