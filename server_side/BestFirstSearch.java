package server_side;

import java.util.ArrayList;
import java.util.HashSet;

public class BestFirstSearch<Solution> extends CommonSearcher<Solution> {

	@Override
	public Solution search(Searchable s) {

		openList.add(s.getInitialState());
		HashSet<State> closedSet = new HashSet<State>();

		while (openList.size() > 0) {
			State n = popOpenList();
			closedSet.add(n);

			if (n.equals(s.getGoalState()))
				return backTrace(n, s.getInitialState());

			ArrayList<State> successors = s.getAllPossibleStates(n);

			for (State state : successors) {

				if (!closedSet.contains(state) && !openList.contains(state)) {
					state.setCameFrom(n);
					openList.add(state);

				} else {
					
					if (n.getCost() + (state.getCost() - state.getCameFrom().getCost()) < state.getCost()) {
						
						if (openList.contains(state))
							state.setCameFrom(n);

						else {
							state.setCameFrom(n);
							closedSet.remove(state);
							openList.add(state);
						}
					}
				}
			}
		}
		return backTrace(s.getGoalState(), s.getInitialState());
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return 0;
	}
}