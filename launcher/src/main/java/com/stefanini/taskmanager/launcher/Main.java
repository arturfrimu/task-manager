package com.stefanini.taskmanager.launcher;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.launcher.command.CommandFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		CommandFactory commandFactory = new CommandFactory();
		Command command = commandFactory.getCommand(args);
		command.execute();
	}
}
