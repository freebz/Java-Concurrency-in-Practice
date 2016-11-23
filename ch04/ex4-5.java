// 예제 4.5  java.awt.Point와 유사하지만 변경 가능한 MutablePoint 클래스

@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() { x = 0; y = 0; }
    public MutablePoint(MutablePoint p) {
	this.x = p.x;
	this.y = p.y;
    }
}
