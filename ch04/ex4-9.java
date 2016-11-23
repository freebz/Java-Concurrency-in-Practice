// 예제 4.9  두 개 이상의 변수에게 스레드 안전성을 위임

public class VisualComponent {
    private final List<KeyListener> keyListeners
	= new CopyOnWriteArrayList<KeyListener>();
    private final List<MouseListener> mouseListeners
	= new CopyOnWriteArrayList<MouseListener>();

    public void addKeyListener(KeyListener listener) {
	keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
	mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
	keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
	mouseListeners.remove(listener);
    }
}
