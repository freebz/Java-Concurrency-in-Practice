// 예제 7.2  1초간 소수를 계산하는 프로그램

List<BigInteger> aSecondOfPrimes() throws InterruptedException {
    PrimeGenerator generator = new PrimeGenerator();
    new Thread(generator).start();
    try {
	SECONDS.sleep(1);
    } finally {
	generator.cancel();
    }
    return generator.get();
}
