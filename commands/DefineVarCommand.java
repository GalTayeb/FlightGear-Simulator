package commands;

import java.util.HashMap;

import expression.ShuntingYard;

public class DefineVarCommand implements Command {

	CommandHandler ch;
	ShuntingYard sy;

	public DefineVarCommand(CommandHandler ch) {

		this.ch = ch;
		sy = new ShuntingYard();
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {

		if (s.length == 2)
			symbolTable.put(s[1], null);

		else if (s[3].contains("bind") || s[2].contains("bind")) {
			ch.symbltablBind.put(s[1].trim(), (s[s.length - 1].trim()));

		} else if (s[2].equals("=")) {
			symbolTable.put(s[1].trim(), (sy.calc(s[3], ch)).calculate());
		}
	}
}