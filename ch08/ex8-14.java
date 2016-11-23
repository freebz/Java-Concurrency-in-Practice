// 예제 8.14  퍼즐 풀기 프레임웍의 Node 클래스

@Immutable
static class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    Node(P pos, M move, Node<P, M> prev) {...}

    List<M> asMoveList() {
	List<M> solution = new LinkedList<M>();
	for (Node<P, M> n = this; n.move != null; n = n.prev)
	    solution.add(0, n.move);
	return solution;
    }
}
