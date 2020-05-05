package test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import commands.CommandHandler;
import interpreter.Lexer;
import interpreter.Parser;
import server_client.DataReaderServer;
import server_client.MyClient;

public class MyInterpreter {

	public static MyClient client;
	public static Double returnValue;

	public static int interpret(String[] lines) {

		client = new MyClient();
		HashMap<String, Double> symbolTable = new HashMap<>();
		HashMap<String, String> symbltbleBind = new HashMap<>();
		ConcurrentHashMap<String, Double> simulatorVars = new ConcurrentHashMap<>();
		simulatorVars.put("simX", (double) 0);
		simulatorVars.put("simY", (double) 0);
		simulatorVars.put("simZ", (double) 0);
		DataReaderServer server = new DataReaderServer(simulatorVars, () -> new String[] { "simX", "simY", "simZ" });

		CommandHandler ch = new CommandHandler(server, client, symbolTable, symbltbleBind, simulatorVars);
		Lexer l = new Lexer();
		Parser p = new Parser(ch, client);

		for (String string : lines) {
			p.Parser(l.Lexer(string));
			;
		}
		server.stop();
		return returnValue.intValue();
	}
}