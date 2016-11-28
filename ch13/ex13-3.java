// 예제 13.3  tryLock 메소드로 락 정렬 문제 해결

public boolean transferMoney(Account fromAcct,
			     Account toAcct,
			     DollarAmount amount,
			     long timeout,
			     TimeUnit unit)
        throws InsufficientFundsException, InterruptedException {
    long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
    long randMod = getRandomDelayModulusNanos(timeout, unit);
    long stopTime = System.nanoTime() + unit.toNanos(timeout);

    while (true) {
	if (fromAcct.lock.tryLock()) {
	    try {
		if (toAcct.lock.tryLock()) {
		    try {
			if (fromAcct.getBalance().compareTo(amount) < 0)
			    throw new InsufficientFundsException();
			else {
			    fromAcct.debit(amount);
			    toAcct.credit(amount);
			    return true;
			}
		    } finally {
			toAcct.lock.unlock();
		    }
		}
	    } finally {
		fromAcct.lock.unlock();
	    }
	}
	if (System.nanoTime() >= stopTime)
	    return false;
	NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
    }
}
