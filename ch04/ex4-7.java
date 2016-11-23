// 예제 4.7  스레드 안전성을 ConcurrentHashMap 클래스에 위임한 추적 프로그램

@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap ;

    public DelegatingVehicleTracker(Map<String, Point> points) {
	locations = new ConcurrentMap<String, Point>(points);
	unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
	return unmodifiableMap;
    }

    public Point getLocation(String id) {
	return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
	if (locations.replace(id, new Point(x, y)) == null)
	    throw new IllegalArgumentException(
                "invalid vehicle name: " + id);
    }
}
