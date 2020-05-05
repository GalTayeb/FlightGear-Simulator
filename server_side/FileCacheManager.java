package server_side;

import java.util.HashMap;
import java.util.Map;

public class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution> {

	private Map<Problem, Solution> hm = new HashMap<Problem, Solution>();

	@Override
	public boolean isSaveSolution(Problem p) {

		return hm.containsKey(p);
	}

	@Override
	public Solution getSolution(Problem p) {

		return hm.get(p);
	}

	@Override
	public void saveSolution(Problem p, Solution s) {

		hm.put(p, s);
	}
}