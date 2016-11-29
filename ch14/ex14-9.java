// 예제 14.9  wait와 notifyAll을 사용해 다시 닫을 수 있도록 구현한 ThreadGate 클래스

@ThreadSafe
public class ThreadGate {
    // 조건 서술어: opened-since(n) (isOpen || generation>n)
    @GuardedBy("this") private boolean isOpen;
    @GuardedBy("this") private int generation;

    public synchronized void close() {
	isOpen = false;
    }

    public synchronized void open() {
	++generation;
	isOpen = true;
	notifyAll();
    }

    // 만족할 때까지 대기: opened-since(generation on entry)
    public synchronized void awiat() throws InterruptedException {
	int arrivalGeneration = generation;
	while (!isOpen && arrivalGeneration == generation)
	    wait();
    }
}
