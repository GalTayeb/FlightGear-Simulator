package expression;

import commands.Command;

public class CommandExpression implements Expression {

	Command c;

	public CommandExpression(Command c) {
		
		this.c = c;
	}

	@Override
	public double calculate() {
		
		return 0;
	}
}