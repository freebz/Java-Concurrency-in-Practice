// 예제 12.3  대기 상태와 인터럽트에 대한 대응을 테스트하는 루틴

void testTakeBlocksWhenEmpty() {
    final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
    Thread taker = new Thread() {
	public void run() {
	    try {
		int unused = bb.take();
		fail(); // 여기에 들어오면 오류!
	    } catch (InterruptedException success) { }
	}};
    try {
	taker.start();
	Thread.sleep(LOOKUP_DETECT_TIMEOUT);
	taker.interrupt();
	taker.join(LOOKUP_DETECT_TIMEOUT);
	assertFalse(taker.isAlive());
    } catch (Exception unexpected) {
	fail();
    }
}
