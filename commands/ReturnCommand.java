package commands;

import java.util.HashMap;

import expression.ShuntingYard;
import server_client.Client;
import test.MyInterpreter;

public class ReturnCommand implements Command {

	Client c;
	CommandHandler ch;
	ShuntingYard sy;

	public ReturnCommand(Client c, CommandHandler ch) {

		this.c = c;
		this.ch = ch;
		sy = new ShuntingYard();
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < s.length; i++)
			sb.append(s[i]);

		MyInterpreter.returnValue = sy.calc(sb.toString(), ch).calculate();
	}
}