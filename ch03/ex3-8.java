// 예제 3.8  생성 메소드에서 this 변수가 외부로 유출되지 않도록 팩토리 메소드를 사용하는 모습

public class SafeListener {
    private final EventListener listener;

    private SafeListener() {
	listener = new EventListener() {
		public void onEvent(Event e) {
		    doSomething(e);
		}
	    };
    }

    public static SafeListener newInstance(EventSource source) {
	SafeListener safe = new SafeListener();
	source.registerListener (safe.listener);
	return safe;
    }
}
