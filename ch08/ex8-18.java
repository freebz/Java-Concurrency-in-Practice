// 예제 8.18  최종 결과가 없다는 사실을 확인하는 기능이 추가된 버전

public class PuzzleSolver<P,M> extends ConcurentPuzzleSolver<P,M> {
    //...
    private fianl AtomicInteger taskCount = new AtomicInteger(0);

    protected Runnable newTask(P p, M m, Node<P,M> n) {
	return new CountingSolverTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask {
	CountingSolverTask(P pos, M move, Node<P, M> prev) {
	    super(pos, move, prev);
	    taskCount.incrementAndGet();
	}
	public void run() {
	    try {
		super.run();
	    } finally {
		if (taskCount.decrementAndGet() == 0)
		    solution.setValue(null);
	    }
	}
    }
}
