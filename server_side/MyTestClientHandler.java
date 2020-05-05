package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyTestClientHandler<Problem, Solution> implements ClientHandler {

	private Solver solver;
	private CacheManager cm;

	public MyTestClientHandler(Solver solver, CacheManager cm) {
		this.solver = solver;
		this.cm = cm;
	}

	@Override
	public void handleClient(InputStream input, OutputStream output) {

		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(output));
		readInputsAndSend(in, out, "end");
	}

	@SuppressWarnings("unchecked")
	private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) {
		try {
			String line;
			Solution answer;
			while (!(line = in.readLine()).equals(exitStr)) {

				if (cm.isSaveSolution(line)) {
					answer = (Solution) cm.getSolution(line);
					out.println(answer);
					out.flush();

				} else {
					answer = (Solution) solver.solve(line);
					cm.saveSolution(line, answer);
					out.println(answer);
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}