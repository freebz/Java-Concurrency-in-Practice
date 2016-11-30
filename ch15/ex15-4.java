// 예제 15.4  ReentrantLock을 사용해 구현한 난수 발생기

@ThreadSafe
public class ReentrantLockPseudoRandom extends PseudoRandom {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    ReentrantLockPseudoRandom(int seed) {
	this.seed = seed;
    }

    public int nextInt(int n) {
	lock.lock();
	try {
	    int s = seed;
	    seed = calculateNext(s);
	    int remainder = s % n;
	    return remainder > 0 ? remainder : remainder + n;
	} finally {
	    lock.unlock();
	}
    }
}
