package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyClientHandler implements ClientHandler {

	CacheManager cm;
	Solver solver;

	public MyClientHandler(CacheManager cm) {

		this.cm = cm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleClient(InputStream input, OutputStream ouput) {

		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(ouput));

		try {
			String line = in.readLine();
			String solve;
			int row = 1;
			StringBuilder sb = new StringBuilder();
			String[] str = line.split(",");
			for (String string : str) {
				sb.append(string + ",");
			}
			while (!(line = in.readLine()).equals("end")) {
				str = line.split(",");
				for (String string : str) {
					sb.append(string + ",");
				}
				row++;
			}

			int[][] mat = new int[str.length][row];
			line = sb + "";
			str = line.split(",");
			row = 0;
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[i].length; j++) {
					mat[i][j] = Integer.parseInt(str[row]);
					row++;
				}
			}
			Maze m = new Maze(mat);
			Searcher searcher = new BestFirstSearch();
			solver = new SolverSearcher<>(searcher);
			m.setIntialState(line = in.readLine());
			m.setGoalState(line = in.readLine());
			if (cm.isSaveSolution(m.toString())) {
				solve = (String) cm.getSolution(m.toString());
			} else {
				solve = (String) solver.solve(m);
				String[] arrows = solve.split("->");
				solve = "";
				String[] arrow1;
				String[] arrow2;
				int x, y;
				for (int i = 0; i < arrows.length - 1; i++) {

					arrow1 = arrows[i].split(",");
					arrow2 = arrows[i + 1].split(",");
					x = Integer.parseInt(arrow2[0]) - Integer.parseInt(arrow1[0]);
					y = Integer.parseInt(arrow2[1]) - Integer.parseInt(arrow1[1]);

					if (x < 0)
						solve += "Up,";
					else if (x > 0)
						solve += "Down,";
					else if (y > 0)
						solve += "Right,";
					else
						solve += "Left'";

				}
				cm.saveSolution(m.toString(), solve);
			}
			out.println(solve.substring(0, solve.length() - 1));
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
}