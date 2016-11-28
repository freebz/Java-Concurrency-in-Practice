// 예제 12.7  자원 유출 테스트

class Big { double[] data = new double[100000]; }

void testLeak() throws InterruptedException {
    BoundedBuffer<Big> bb = new BoundedBuffer<Big>(CAPACITY);
    int heapSize1 = /* 힙 스냅샷 */;
    for (int i = 0; i < CAPACITY; i++)
	bb.put(new Big());
    for (int i = 0; i < CAPACITY; i++)
	bb.take();
    int heapSize2 = /* 힙 스냅샷 */;
    assertTrue(Math.abs(heapSize1-heapSize2) < THRESHOLD);
}
