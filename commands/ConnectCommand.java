package commands;

import java.util.HashMap;

import server_client.Client;

public class ConnectCommand implements Command {

	Client c;

	public ConnectCommand(Client c) {

		this.c = c;
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {

		String ip = s[1];
		int port = Integer.parseInt(s[2]);
		c.connect(ip, port);
	}
}