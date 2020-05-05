package server_side;

public class StringReverser<Problem, Solution> implements Solver<Problem, Solution> {

	@SuppressWarnings("unchecked")
	@Override
	public Solution solve(Problem p) {
		
		return (Solution) new StringBuilder((String) p).reverse().toString();
	}
}