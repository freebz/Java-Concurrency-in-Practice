// 예제 8.15  순차적으로 동작하는 퍼즐 풀기 프로그램

public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
	this.puzzle = puzzle;
    }

    public List<M> solve() {
	P pos = puzzle.initialPosition();
	return search(new Node<P, M>(pos, null, null));
    }

    private List<M> search(Node<P, M> node) {
	if (!seen.contains(node.pos)) {
	    seen.add(node.pos);
	    if (puzzle.isGoal(node.pos))
		return node.asMoveList();
	    for (M move : puzzle.legalMoves(node.pos)) {
		Node<P, M> child = new Node<P, M>(pos, move, node);
		List<M> result = search(child);
		if (result != null)
		    return result;
	    }
	}
	return null;
    }

    static class Node<P, M> { /* 예제 8.14 */ }
}
