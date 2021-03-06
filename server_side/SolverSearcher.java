package server_side;

public class SolverSearcher<Problem, Solution> implements Solver<Problem, Solution> {

	private Searcher s;

	public SolverSearcher(Searcher s) {

		this.s = s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Solution solve(Problem p) {

		return (Solution) s.search((Searchable) p);
	}
}