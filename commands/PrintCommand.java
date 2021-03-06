package commands;

import java.util.HashMap;

public class PrintCommand implements Command {

	CommandHandler ch;

	public PrintCommand(CommandHandler ch) {

		this.ch = ch;
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {

		System.out.println(ch.simulatorVars.get(ch.symbltablBind.get(s[1].trim())));
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}