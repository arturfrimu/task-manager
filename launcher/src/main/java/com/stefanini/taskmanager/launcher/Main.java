package com.stefanini.taskmanager.launcher;

import static com.stefanini.taskmanager.launcher.command.CommandFactory.getCommand;

public class Main {
	public static void main(String[] args) throws Exception {
		getCommand(args).execute();
	}
}
