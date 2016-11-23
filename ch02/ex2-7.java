// 예제 2.7  암묵적인 락이 재진입 가능하지 않았다면 데드락에 빠졌을 코드

public class Widget {
    public synchronized void doSomething() {
	// ...
    }
}

public class LoggingWidget extends Widget {
    public synchronized void doSomething() {
	System.out.println(toString() + ": calling doSomething");
	super.doSomething();
    }
}
