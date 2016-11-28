// 예제 13.5  인터럽트를 걸 수 있는 락 확보 방법

public boolean sendOnSharedLine(String message)
        throws InterruptedException {
    lock.lockInterruptibly();
    try {
	return cancellableSendOnSharedLine(message);
    } finally {
	lock.unlock();
    }
}

private boolean cancellableSendOnSharedLine(String message)
    throws InterruptedException { ... }
