// 예제 8.11  순차적인 재귀 함수를 병렬화한 모습

public<T> void sequentialRecursive(List<Node<T>> nodes,
				   Collection<T> results) {
    for (Node<T> n : nodes) {
	results.add(n.compute());
	sequentialRecursive(n.getChildren(), results);
    }
}

public<T> void parallelRecursive(final Executor exec,
				 List<Node<T>> nodes,
				 final Collection<T> results) {
    for (final Node<T> n : nodes) {
	exec.execute(new Runnable() {
	    public void run() {
		results.add(n.compute());
	    }
	});
	parallelRecursive(exec, n.getChildren(), results);
    }
}
