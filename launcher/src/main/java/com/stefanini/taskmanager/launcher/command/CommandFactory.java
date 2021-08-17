package com.stefanini.taskmanager.launcher.command;

import com.stefanini.taskmanager.launcher.command.impl.*;
import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

public class CommandFactory {
    public static final String CREATE_USR_CMD = "-createUser";
    public static final String SHOW_ALL_USR_CMD = "-showAllUsers";
    public static final String ADD_TASK_CMD = "-addTask";
    public static final String SHOW_TASKS_CMD = "-showTasks";
    public static final String ADD_TASK_TO_GROUP = "-addTaskToGroup";

    static Logger logger = Logger.getLogger(CommandFactory.class);

    public Command getCommand(String [] args) throws IOException {
        PropertyConfigurator.configure(ReadFromProperties.read("log4j-console.properties"));
        Command command = null;
        switch(args[0]) {
            case CREATE_USR_CMD:
                command = new SaveUserToDataBaseImpl(args[1], args[2], args[3]);
                break;
            case SHOW_ALL_USR_CMD:
                command = new ShowAllUsersImpl();
                break;
            case ADD_TASK_CMD:
                command = new AddTaskToUserImpl(args[1], args[2], args[3]);
                break;
            case SHOW_TASKS_CMD:
                command = new ShowUserTasksImpl(args[1]);
                break;
            case ADD_TASK_TO_GROUP:
                command = new AddTaskToGroupImpl(args[1], args[2], args[3]);
                break;
            default:
                logger.error("Incorrect arguments");
        }
        return command;
    }
}
