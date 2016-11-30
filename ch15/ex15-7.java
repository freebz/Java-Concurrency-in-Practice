// 예제 15.7  마이클-스콧 넌블로킹 큐 알고리즘 가운데 항목 추가 부분(Michael and Scott, 1996)

@ThreadSafe
public class LinkedQueue <E> {
    private static class Node <E> {
	final E item;
	final AtomicReference<Node<E>> next;

	public Node(E item, Node<E> next) {
	    this.item = item;
	    this.next = new AtomicReference<Node<E>>(next);
	}
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference <Node<E>> head
	= new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail
	= new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
	Node<E> newNode = new Node<E>(item, null);
	while (true) {
	    Node<E> curTail = tail.get();
	    Node<E> tailNext = curTail.next.get();
	    if (curTail == tail.get()) {
		if (tailNext != null) {
		    // 큐는 중간 상태이고, 꼬리 이동
		    tail.compareAndSet(curTail, tailNext);
		} else {
		    // 평온한 상태에서 항목 추가 시도
		    if (curTail.next.compareAndSet(null, newNode)) {
			// 추가 작업 성공, 꼬리 이동 시도
			tail.compareAndSet(curTail, newNode);
			return true;
		    }
		}
	    }
	}
    }
}
