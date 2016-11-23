// 예제 3.7  this 클래스에 대한 참조를 외부에 공개하는 상황. 이런 코드는 금물!

public class ThisEscape {
    public ThisEscape(EventSource source) {
	source.registerListener(
	   new EventListener() {
	       public void onEvent(Event e) {
		   doSomething(e);
	       }
	   });
    }
}
