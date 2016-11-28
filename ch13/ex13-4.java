// 예제 13.4  일정 시간 이내에 락을 확보하는 모습

public boolean trySendOnSharedLine(String message,
				   long timeout, TimeUnit unit)
                                   throws InterruptedException {
    long nanosToLock = unit.toNanos(timeout)
	             - estimatedNanosToSend(message);
    if (!lock.tryLock(nanosToLock, NANOSECONDS))
	return false;
    try {
	return sendOnSharedLine(message);
    } finally {
	lock.unlock();
    }
}
