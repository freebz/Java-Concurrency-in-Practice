// 예제 5.12  FutureTask를 사용해 추후 필요한 데이터를 미리 읽어들이는 모습

public class Preloader {
    private final FutureTask<ProductInfo> future =
	new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
		public ProductInfo call() throws DataLoadException {
		    return loadProductInfo();
		}
	    });
    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get()
	   throws DataLoadException, InterruptedException {
	try {
	    return future.get();
	} catch (ExecutionException e) {
	    Throwable cause = e.getCause();
	    if (cause instanceof DataLoadException)
		throw (DataLoadException) cause;
	    else
		throw launderThrowable(cause);
	}
    }
}
