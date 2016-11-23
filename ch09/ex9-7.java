// 예제 9.7  작업 중단, 작업 중단 알림, 진행 상태 알림 등의 기능을 갖고 있는 작업 클래스

abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
	public Computation() {
	    super(new Callable<V>() {
		public V call() throws Exception {
		    return BackgroundTask.this.compute();
		}
	    });
	}
	protected final void done() {
	    GuiExecutor.instance().execute(new Runnable() {
		public void run() {
		    V value = null;
		    Throwable thrown = null;
		    boolean cancelled = false;
		    try {
			value = get();
		    } catch (ExecutionException e) {
			thrown = e.getCause();
		    } catch (CancellationException e) {
			cancelled = true;
		    } catch (InterruptedException consumed) {
		    } finally {
			coCompletion(value, thrown, cancelled);
		    }
		};
	    });
	}
    }
    protected void setProgress(final int current, final int max) {
	GuiExecutor.instance().execute(new Runnable() {
	    public void run() { onProgress(current, max); }
	});
    }
    // 백그라운드 작업 스레드에서 호출함
    protected abstract V compute() throws Exception;
    // 이벤트 스레드에서 호출함
    protected void onCompletion(V result, Throwable exception,
				boolean cancelled) { }
    protected void onPregress(int current, int max) { }
    // 기타 여러 가지 Future 메소드
}
