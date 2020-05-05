package commands;

import java.util.HashMap;

import server_client.DataReaderServer;

public class OpenServerCommand implements Command {

	DataReaderServer server;

	public OpenServerCommand(DataReaderServer server) {

		this.server = server;
	}

	@Override
	public void doCommand(String[] s, HashMap<String, Double> symbolTable) {

		int port = Integer.parseInt(s[1]);
		server.openServer(port);
	}
}