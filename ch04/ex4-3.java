// 예제 4.3  private이면서 final인 변수를 사용해 동기화

public class PrivateLock {
    privte final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod() {
	synchronized(myLock) {
	    // widget 변수의 값을 읽거나 변경
	}
    }
}
