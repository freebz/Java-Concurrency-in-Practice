// 예제 12.2  BoundedBuffer 클래스의 기능을 테스트하는 기본 테스트 케이스

class BoundedBufferTest extends TestCase {
    void testIsEmptyWhenConstructed() {
	BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
	assertTrue(bb.isEmpty());
	assertFalse(bb.isFull());
    }

    void testIsFullAfterPuts() throws InterruptedException {
	BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
	for (int i = 0; i < 10; i++)
	    bb.put(i);
	assertTrue(bb.isFull());
	assertFalse(bb.isEmpty());
    }
}
