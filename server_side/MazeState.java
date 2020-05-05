package server_side;

public class MazeState extends State<String> {

	public MazeState(String state) {

		super(state);
		setCameFrom(null);
	}
}