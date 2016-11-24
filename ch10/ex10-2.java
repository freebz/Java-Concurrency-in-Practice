// 예제 10.2  동적인 락 순서에 의한 데드락. 이런 코드는 금물!

// 데드락 위험!
public void transferMoney (Account fromAccount,
			   Account toAccount,
			   DollarAmount amount)
        throws InsufficientFundsException {
    synchronized (fromAccount) {
	synchronized (toAccount) {
	    if (fromAccount.getBalance().compareTo(amount) < 0)
		throw new InsufficientFundsException();
	    else {
		fromAccount.debit(amount);
		toAccount.credit(amount);
	    }
	}
    }
}
