// 예제 1.2  스레드 안전한 일련번호 생성 프로그램

@ThreadSafe
public class Sequence {

    @GuardedBy("this") private int value;

    public synchronized int getNext() {
	return value++;
    }
}
