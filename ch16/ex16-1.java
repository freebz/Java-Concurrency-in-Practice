// 예제 16.1  제대로 동기화되지 않아 어이 없는 결과를 출력하기도 하는 프로그램. 이런 코드는 금물!

public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args)
	    throws InterruptedException {
	Thread one = new Thread(new Runnable() {
	    public void run() {
		a = 1;
		x = b;
	    }
	});
	Thread other = new Thread(new Runnable() {
	    public void run() {
		b = 1;
		y = a;
	    }
	});
	one.start(); other.start();
	one.join(); other.join();
	System.out.println("("+ x + "," + y + ")");
    }
}
