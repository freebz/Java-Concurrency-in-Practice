// 예제 5.7  ConcurrentMap 인터페이스

public interface ConcurrentMap<K,V> extends Map<K,V> {
    // key라는 키가 없는 경우에만 value 추가
    V putIfAbsent(K key, V value);

    // key라는 키가 vlaue 값을 갖고 있는 경우 제거
    boolean remove(K key, V value);

    // key라는 키가 oldValue 값을 갖고 있는 경우 newValue로 치환
    boolean replace(K key, V oldValue, V newValue);

    // key라는 키가 들어 있는 경우에만 newValue로 치환
    V replace(K key, V newValue);
}
