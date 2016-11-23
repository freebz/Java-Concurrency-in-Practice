// 예제 4.4  모니터 기반의 차량 추적 프로그램

@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker (
            Map<String, MutablePoint> locations) {
	this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
	return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
	MutablePoint loc = locations.get(id);
	return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
	MutablePoint loc = locations.get(id);
	if (loc == null)
	    throw new IllegalArgumentException ("No such ID: " + id);
	loc.x = x;
	loc.y = y;
    }
    private static Map<String, MutablePoint> deepCopy(
            Map<String, MutablePoint> m) {
	Map<String, MutablePoint> result =
	    new HashMap<String, MutablePoint>();
	for (String id : m.keySet())
	    result.put(id, new MutablePoint(m.get(id)));
	return Collections.unmodifiableMap (result);
    }
}

public class MutablePoint { /* 예제 4.5 */ }
