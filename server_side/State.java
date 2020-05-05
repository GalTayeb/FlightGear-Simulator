package server_side;

public class State<T> {

	protected T state;
	private double cost;
	private State cameFrom;

	public State() {
		
		this.state = null;
		this.cost = 0;
		this.cameFrom = null;
	}

	public State(T state) {
		
		this.state = state;
	}

	public T getState() {
		
		return state;
	}

	public void setState(T state) {
		
		this.state = state;
	}

	public double getCost() {

		if (getCameFrom() == null)
			return cost;
		else
			return getCameFrom().getCost() + cost;
	}

	public void setCost(double cost) {
		
		this.cost = cost;
	}

	public State getCameFrom() {
		
		return cameFrom;
	}

	public void setCameFrom(State cameFrom) {
		
		this.cameFrom = cameFrom;
	}

	public boolean equals(State s) {
		
		return state.equals(s.getState());
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
}