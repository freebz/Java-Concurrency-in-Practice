// 예제 4.11  값 변경이 가능하고 스레드 안전성도 확보한 SafePoint 클래스

@ThreadSafe
public class SafePoint {
    @GuardedBy("this") private int x, y;

    private SafePoint(int[] a) { this(a[0], a[1]); }

    public SafePoint(SafePoint p) { this(p.get()); }

    public SafePoint(int x, int y) {
	this.set(x, y);
    }

    public synchronized int[] get() {
	return new int[] { x, y };
    }

    public synchronized void set(int x, int y) {
	this.x = x;
	this.y = y;
    }
}
