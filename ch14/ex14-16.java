// 예제 14.16  Semaphore 클래스의 tryAcquireShared 메소드와 tryReleaseShared 메소드

protected int tryAcquireShared(int acquires) {
    while (true) {
	int available = getState();
	int remaining = available - acquires;
	if (remaining < 0
	        || compareAndSetState(available, remaining))
	    return remaining;
    }
}

protected boolean tryReleaseShared(int release) {
    while (true) {
	int p = getState();
	if (compareAndSetState(p, p + release))
	    return true;
    }
}
