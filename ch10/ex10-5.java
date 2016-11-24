// 예제 10.5  객체 간에 발생하는 락 순서에 의한 데드락. 이런 코드는 금물!

// 데드락 위험!
class Taxi {
    @GuardedBy("this") private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
	this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
	return location;
    }

    public synchronized void setLocation(Point location) {
	this.location = location;
	if (location.equals(destination))
	    dispatcher.notifyAvailable(this);
    }
}

class Dispatcher {
    @GuardedBy("this") private final Set<Taxi> taxis;
    @GuardedBy("this") private final Set<Taxi> availableTaxis;

    public Dispatcher() {
	taxis = new HashSet<Taxi>();
	availableTaxis = new HashSet<Taxi>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
	availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
	Image image = new Image();
	for (Taxi t : taxis)
	    image.drawMarker(t.getLocation());
	return image;
    }
}
