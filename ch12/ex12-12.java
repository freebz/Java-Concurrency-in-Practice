// 예제 12.12  배리어 기반 타이머를 사용한 테스트

public void test() {
    try {
	timer.clear();
	for (int i = 0; i < nPairs; i++) {
	    pool.execute(new Producer());
	    pool.execute(new Consumer());
	}
	barrier.await();
	barrier.await();
	long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
	System.out.print("Throughput: " + nsPerItem + " ns/item");
	assertEquals(putSum.get(), takeSum.get());
    } catch (Exception e) {
	throw new RuntimeException(e);
    }
}
