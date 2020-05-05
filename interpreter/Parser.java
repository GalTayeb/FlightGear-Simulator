package interpreter;

import java.util.Arrays;

import commands.AssignmentCommand;
import commands.Command;
import commands.CommandHandler;
import server_client.Client;

public class Parser {

	CommandHandler ch;
	Client client;
	boolean loopCommand;
	boolean ifCommand;

	public Parser(CommandHandler ch, Client c) {
		
		this.ch = ch;
		this.client = c;
		loopCommand = false;
		ifCommand = false;
	}

	public void Parser(String[] str) {

		String[] str2 = Arrays.stream(str).filter(value -> !value.isEmpty()).filter((string) -> !string.equals(" "))
				.toArray(String[]::new);

		Command c = ch.commandHashMap.get(str2[0]);

		if (c == null && str2[1].equals("=")) {
			c = new AssignmentCommand(ch, client);
		} else if ((c.getClass().getSimpleName() + "").equals("DisconnectCommand")) {
			c.doCommand(str2, ch.symbolTable);
			return;
		}
		if ((c.getClass().getSimpleName() + "").equals("LoopCommand"))
			loopCommand = true;
		else if ((c.getClass().getSimpleName() + "").equals("IfCommand"))
			ifCommand = true;
		if (str2[str2.length - 2].contains("}") && loopCommand == true) {
			loopCommand = false;
			c = ch.commandHashMap.get("while");
		} else if (str2[str2.length - 2].contains("}") && ifCommand == true) {
			ifCommand = false;
			c = ch.commandHashMap.get("if");
		}
		if (loopCommand == false && ifCommand == false)
			c.doCommand(str2, ch.symbolTable);
	}
}