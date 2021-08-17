package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class SaveUserToDataBaseImpl implements Command {
    private UserService userService;
    private String firstName;
    private String lastName;
    private String userName;

    public SaveUserToDataBaseImpl(String firstName, String lastName, String userName) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
    }

    @Override
    public void execute() throws Exception {
        userService = new UserServiceImpl();
        userService.saveUser(firstName, lastName, userName);
    }
}
