package expression;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import commands.CommandHandler;

public class ShuntingYard {

	public Expression calc(String exp, CommandHandler ch) {

		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<Expression> stackExp = new Stack<Expression>();

		String[] split = exp.split("(?<=[-+*/()])|(?=[-+*/()])|\" \"");
		for (String s : split) {
			if (s.isEmpty())
				continue;

			s = s.trim();

			if (isDouble(s) || s.matches("\\w+")) {
				queue.add(s);
			} else {
				switch (s) {
				case "/":
				case "*":
				case "(":
					stack.push(s);
					break;
				case "+":
				case "-":
					while (!stack.empty() && (!stack.peek().equals("("))) {
						queue.add(stack.pop());
					}
					stack.push(s);
					break;
				case ")":
					while (!stack.peek().equals("(")) {
						queue.add(stack.pop());
					}
					stack.pop();
					break;
				}
			}
		}
		while (!stack.isEmpty()) {
			queue.add(stack.pop());
		}

		for (String str : queue) {
			if (isDouble(str)) {
				stackExp.push(new Number(Double.parseDouble(str)));
			} else if (str.matches("\\w+")) {
				stackExp.push(new VarNumber(str, ch));
			} else {
				Expression right = stackExp.pop();
				Expression left = stackExp.pop();

				switch (str) {
				case "/":
					stackExp.push(new Div(left, right));
					break;
				case "*":
					stackExp.push(new Mul(left, right));
					break;
				case "+":
					stackExp.push(new Plus(left, right));
					break;
				case "-":
					stackExp.push(new Minus(left, right));
					break;
				}
			}
		}
		return stackExp.pop();
	}

	private static boolean isDouble(String val) {

		try {
			Double.parseDouble(val);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}