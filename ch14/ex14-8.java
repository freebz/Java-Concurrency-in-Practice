// 예제 14.8  BoundedBuffer.put 메소드에 조건부 알림 방법을 적용한 모습

public synchronized void put(V v) throws InterruptedException {
    while (isFull())
	wait();
    boolean wasEmpty = isEmpty();
    doPut(v);
    if (wasEmpty)
	notifyAll();
}
