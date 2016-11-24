// 예제 10.6  객체 간의 데드락을 방지하기 위해 오픈 호출을 사용하는 모습

@ThreadSafe
class Taxi {
    @GuardedBy("this") private Point location, destination;
    private final Dispatcher dispatcher;
    //...
    public synchronized Point getLocation() {
	return location;
    }

    public void setLocation(Point location) {
	boolean reachedDestination;
	synchronized (this) {
	    this.location = location;
	    reachedDestination = location.equals(destination);
	}
	if (reachedDestination )
	    dispatcher.notifyAvailable(this);
    }
}

@ThreadSafe
class Dispatcher {
    @GuardedBy("this") private final Set<Taxi> taxis;
    @GuardedBy("this") private final Set<Taxi> availableTaxis;
    //...
    public synchronized void notifyAvailable(Taxi taxi) {
	availableTaxis.add(taxi);
    }

    public Image getImage() {
	Set<Taxi> copy;
	synchronized (this) {
	    copy = new HashSet<Taxi>(taxis);
	}
	Image image = new Image();
	for (Taxi t : copy)
	    image.drawMarker(t.getLocation());
	return image;
    }
}
