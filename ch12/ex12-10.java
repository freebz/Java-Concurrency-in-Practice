// 예제 12.10  Thread.yield 메소드를 사용해 교차 실행 가능성을 높이는 방법

public synchronized void transferCredits (Account from,
					  Account to,
					  int amount) {
    from.setBalance(from.getBalance() - amount);
    if (random.nextInt(1000) > THRESHOLD)
	Thread.yield();
    to.setBalance(to.getBalance() + amount);
}
