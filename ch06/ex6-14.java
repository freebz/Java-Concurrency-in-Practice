// 예제 6.14  ExecutorCompletionService에서 사용하는 QueueingFuture 클래스

private class QueueingFuture<V> extends FutureTask<V> {
    QueueingFuture(Callable<V> c) { super(c); }
    QueueingFuture(Runnable t, V r) { super(t, r); }

    protected void done() {
	completionQueue.add(this);
    }
}
