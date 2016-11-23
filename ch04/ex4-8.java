// 예제 4.8  위치 정보에 대한 고정 스냅샷을 만들어 내는 메소드

public Map<String, Point> getLocations() {
    return Collections.unmodifiableMap(
        new HashMap<String, Point>(locations));
}
