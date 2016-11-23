// 예제 5.19  Memoizer 최종 버전

public class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache
	= new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) { this.c = c; }

    public V compute(final A arg) throws InterruptedException {
	while (true) {
	    Future<V> f = cache.get(arg);
	    if (f == null) {
		Callable<V> eval = new Callable<V>() {
		    public V call() throws InterruptedException {
			return c.compute(arg);
		    }
		};
		FutureTask<V> ft = new FutureTask<V>(eval);
		f = cache.putIfAbsend(arg, ft);
		if (f == null) { f = ft; ft.run(); }
	    }
	    try {
		return f.get();
	    } catch (ExecutionException e) {
		throw launderThrowable(e.getCause());
	    }
	}
    }
}
