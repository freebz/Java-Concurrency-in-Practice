// 예제 6.9  Timer를 사용할 때 발생할 수 있는 오류 상황

public class OutOfTime {
    public static void main(String[] args) throws Exception {
	Timer timer = new Timer();
	timer.schedule(new ThrowTask(), 1);
	SECONDS.sleep(1);
	timer.schedule(new ThrowTask(), 1);
	SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {
	public void run() { throw new RuntimeException(); }
    }
}
