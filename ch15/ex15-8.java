// 예제 15.8  ConcurrentLinkedQueue 클래스에서 단일 연산 필드 업데이터를 사용하는 모습

private class Node<E> {
    private final E item;
    private volatile Node<E> next;

    public Node(E item) {
	this.item = item;
    }
}

private static AtomicReferenceFieldUpdater<Node, Node> nextUpdater
    = AtomicReferenceFieldUpdater.newUpdater(
            Node.class, Node.class, "next");
