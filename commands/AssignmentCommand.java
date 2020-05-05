package commands;

import java.util.HashMap;

import expression.ShuntingYard;
import server_client.Client;

public class AssignmentCommand implements Command {

	CommandHandler ch;
	ShuntingYard sy;
	Client c;

	public AssignmentCommand(CommandHandler ch, Client c) {
		
		this.ch = ch;
		sy = new ShuntingYard();
		this.c = c;
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {
		
		s[0] = s[0].trim();
		if (!s[2].equals("bind"))
		{
			if (ch.symbltablBind.containsKey(s[0])) {
				ch.simulatorVars.put(ch.symbltablBind.get(s[0]), sy.calc(s[s.length - 1], ch).calculate());
				if (c.getSocket() != null)
					c.setPathValue(ch.symbltablBind.get(s[0].trim()), sy.calc(s[s.length - 1], ch).calculate());
			} else {
				symbolTable.put(s[0], sy.calc(s[s.length - 1], ch).calculate());
			}
		} else
			ch.symbltablBind.put(s[0], s[s.length - 1]);
	}
}