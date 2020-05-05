package commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import expression.ShuntingYard;
import server_client.DataReaderServer;

public abstract class ConditionCommands {

	DataReaderServer server;
	ShuntingYard sy;
	CommandHandler ch;

	public ConditionCommands(DataReaderServer server, CommandHandler ch) {
		
		this.server = server;
		sy = new ShuntingYard();
		this.ch = ch;
	}

	protected List<String[]> convertArgumentsToListOfCommands(String string) {

		List<String> listOfCommands = new LinkedList<>();
		List<String> listOfLines = Arrays.asList(string.split("/n"));
		listOfCommands = listOfLines.subList(1, listOfLines.size() - 2);

		return listOfCommands.stream().map(line -> line
				.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))"))
				.collect(Collectors.toList());
	}

	protected boolean condition(String variable, String operator, String value) {

		switch (operator) {

		case "<":
			return sy.calc(variable, ch).calculate() < sy.calc(value, ch).calculate();
		case ">":
			return sy.calc(variable, ch).calculate() > sy.calc(value, ch).calculate();
		case "<=":
			return sy.calc(variable, ch).calculate() <= sy.calc(value, ch).calculate();
		case ">=":
			return sy.calc(variable, ch).calculate() >= sy.calc(value, ch).calculate();
		case "==":
			return sy.calc(variable, ch).calculate() == sy.calc(value, ch).calculate();
		case "!=":
			return sy.calc(variable, ch).calculate() != sy.calc(value, ch).calculate();
			
		default:
			return false;
		}
	}
}