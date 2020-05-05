package server_side;

import java.util.ArrayList;
import java.util.Arrays;

public class Maze implements Searchable {

	private State initialState;
	private State goalState;
	private int[][] maze;
	private MazeState[][] stateMaze;

	public Maze() {

		initialState = null;
		goalState = null;
		maze = null;
	}

	public Maze(int[][] maze) {

		this.maze = maze;
		stateMaze = new MazeState[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {

			for (int j = 0; j < maze[i].length; j++) {

				stateMaze[i][j] = new MazeState(i + "," + j);
				stateMaze[i][j].setCost(maze[i][j]);
			}
		}
	}

	@Override
	public State getInitialState() {

		return initialState;
	}

	public void setIntialState(String s) {

		String[] loc = s.split(",");
		int row, col;
		row = Integer.parseInt(loc[0]);
		col = Integer.parseInt(loc[1]);
		initialState = stateMaze[row][col];
	}

	@Override
	public State getGoalState() {

		return goalState;
	}

	public void setGoalState(String s) {

		String[] loc = s.split(",");
		int row, col;
		row = Integer.parseInt(loc[0]);
		col = Integer.parseInt(loc[1]);
		goalState = stateMaze[row][col];
	}

	@Override
	public ArrayList<State> getAllPossibleStates(State s) {

		String[] loc = ((String) s.getState()).split(",");
		int row, col, tmp = 0;
		row = Integer.parseInt(loc[0]);
		col = Integer.parseInt(loc[1]);
		State right = null, left = null, up = null, down = null;

		ArrayList<State> al = new ArrayList<State>();
		if (row == 0)
			tmp += 3;
		if (row == maze.length - 1)
			tmp += 7;
		if (col == 0)
			tmp += 5;
		if (col == maze[row].length - 1)
			tmp += 11;
		switch (tmp) {
		
		case 8:
			right = stateMaze[row][col + 1];
			down = stateMaze[row + 1][col];
			break;
			
		case 3:
			right = stateMaze[row][col + 1];
			down = stateMaze[row + 1][col];
			left = stateMaze[row][col - 1];
			break;
			
		case 5:
			right = stateMaze[row][col + 1];
			down = stateMaze[row + 1][col];
			up = stateMaze[row - 1][col];
			break;
			
		case 7:
			right = stateMaze[row][col + 1];
			up = stateMaze[row - 1][col];
			left = stateMaze[row][col - 1];
			break;
			
		case 11:
			up = stateMaze[row - 1][col];
			left = stateMaze[row][col - 1];
			down = stateMaze[row + 1][col];
			break;
			
		case 14:
			left = stateMaze[row][col - 1];
			down = stateMaze[row + 1][col];
			break;
			
		case 12:
			up = stateMaze[row - 1][col];
			right = stateMaze[row][col + 1];
			break;
			
		case 18:
			up = stateMaze[row - 1][col];
			left = stateMaze[row][col - 1];
			break;
			
		default:
			up = stateMaze[row - 1][col];
			left = stateMaze[row][col - 1];
			right = stateMaze[row][col + 1];
			down = stateMaze[row + 1][col];
			break;
		}
		
		State[] st = { right, left, up, down };
		
		for (State state : st) {
			
			if (state != null)
				if (state != s.getCameFrom()) {
					al.add(state);
				}
		}
		return al;
	}
}