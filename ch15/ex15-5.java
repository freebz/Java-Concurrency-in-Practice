// 예제 15.5  AtomicInteger를 사용해 구현한 난수 발생기

@ThreadSafe
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;

    AtomicPseudoRandom(int seed) {
	this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
	while (true) {
	    int s = seed.get();
	    int nextSeed = calculateNext(s);
	    if (seed.compareAndSet(s, nextSeed)) {
		int remainder = s % n;
		return remainder > 0 ? remainder : remainder + n;
	    }
	}
    }
}
