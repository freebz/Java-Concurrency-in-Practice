// 예제 14.7  상태 종속적인 메소드의 표준적인 형태

void stateDependentMethod() throws InterruptedException {
    // 조건 서술어는 반드시 락으로 동기화된 이후에 확인해야 한다.
    synchronized(lock) {
	while (!conditionPredicate())
	    lock.wait();
	// 객체가 원하는 상태에 맞춰졌다.
    }
}
