// 예제 2.3  늦은 초기화에서 발생한 경쟁 조건. 이런 코드는 금물!

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
	if (instance == null)
	    instance = new ExpensiveObject();
	return instance;
    }
}
