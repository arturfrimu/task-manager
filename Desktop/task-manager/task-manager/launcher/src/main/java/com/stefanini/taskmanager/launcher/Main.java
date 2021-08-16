package com.stefanini.taskmanager.launcher;

import static com.stefanini.taskmanager.launcher.command.CommandFactory.getCommand;

public class Main {
    public static void main(String[] args) throws Exception {
        String firstName = "-fn='ANDREI'";
        String lastName = "-ln='CEBAN'";
        String username = "-un='Artur.0201200997yufu'";
        String title = "-tt='TITLE'";
        String description = "-td='DESCRIPTION'";
        String groupName =  "-gn='IT'";

        String [] arguments1 = {"-createUser", firstName , lastName, username};
        String [] arguments3 = {"-addTask", username, title, description};
        getCommand(arguments1).execute();
        getCommand(arguments3).execute();
    }
}
