// 예제 3.1  변수를 공유하지만 동기화되지 않은 예제. 이런 코드는 금물!

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
	public void run() {
	    while (!ready)
		Thread.yield();
	    System.out.println(number);
	}
    }

    public static void main(String[] args) {
	new ReaderThread().start();
	number = 42;
	ready = true;
    }
}
