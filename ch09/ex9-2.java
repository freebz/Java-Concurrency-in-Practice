// 예제 9.2  SwingUtilities를 활용하는 Executor

public class GuiExecutor extends AbstractExecutorService {
    // 싱글턴 객체의 생성 메소드는 private이고, public인 팩토리 메소드를 사용
    private static final GuiExecutor instance = new GuiExecutor();

    private GuiExecutor() { }

    public static GuiExecutor instance() { return instance; }

    public void execute(Runnable r) {
	if (SwingUtilities.isEventDispatchThread())
	    r.run();
	else
	    SwingUtilities.invokeLater(r);
    }

    // Executor의 몇 가지 기본 메소드는 생략
}
