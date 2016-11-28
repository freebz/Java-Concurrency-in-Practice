// 예제 13.7  읽기-쓰기 락을 사용해 Map을 확장

public class ReadWriteMap<K,V> {
    private final Map<K,V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public ReadWriteMap(Map<K,V> map) {
	this.map = map;
    }

    public V put(K key, V value) {
	w.lock();
	try {
	    return map.put(key, value);
	} finally {
	    w.unlock();
	}
    }
    // remove(), putAll(), clear() 메소드도 put()과 동일하게 구현

    public V get(Object key) {
	r.lock();
	try {
	    return map.get(key);
	} finally {
	    r.unlock();
	}
    }
    // 다른 읽기 메소드도 get()과 동일하게 구현
}
