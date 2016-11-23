// 예제 7.12  newTaskFor를 사용해 표준을 따르지 않은 작업 중단 방법 적용

public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}

@ThreadSafe
public class CancellingExecutor extends ThreadPoolExecutor {
    ...
    protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
	if (callable instanceof CancellableTask)
	    return ((CancellableTask<T>) callable).newTask();
	else
	    return super.newTaskFor(callable);
    }
}

public abstract class SocketUsingTask<T>
        implements CancellableTask<T> {
    @GuardedBy("this") private Socket socket;

    protected synchronized void setSocket(Socket s) { socket = s; }

    public synchronized void cancel() {
	try {
	    if (socket != null)
		socket.close();
	} catch (IOException ignored) { }
    }

    public FunnableFuture<T> newTask() {
	return new FutureTask<T>(this) {
	    public boolean cancel(boolean mayInterruptIfRunning) {
		try {
		    SocketUsingTask.this.cancel();
		} finally {
		    return super.cancel(mayInterruptIfRunning);
		}
	    }
	};
    }
}
