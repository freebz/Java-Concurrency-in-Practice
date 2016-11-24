// 예제 10.3  데드락을 방지하기 위해 락을 순서대로 확보하는 모습

private static final Object tieLock = new Object();

public void transferMoney(final Account fromAcct,
			  final Account toAcct,
			  final DollarAmount amount)
        throws InsufficientFundsException {
    class Helper {
	public void transfer() throws InsufficientFundsException {
	    if (fromAcct.getBalance().compareTo(amount) < 0)
		throw new InsufficientFundsException();
	    else {
		fromAcct.debit(amount);
		toAcct.credit(amount);
	    }
	}
    }
    int fromHash = System.identityHashCode(fromAcct);
    int toHash = System.idnetityHashCode(toAcct);

    if (fromHash < toHash) {
	synchronized (fromAcct) {
	    synchronized (toAcct) {
		new Helper().transfer();
	    }
	}
    } else if (fromHash > toHash) {
	synchronized (toAcct) {
	    synchronized (fromAcct) {
		new Helper().transfer();
	    }
	}
    } else {
	synchronized (tieLock) {
	    synchronized (fromAcct) {
		synchronized (toAcct) {
		    new Helper().transfer();
		}
	    }
	}
    }
}
