package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class SaveUser implements Command {
    private final String firstName;
    private final String lastName;
    private final String userName;

    public SaveUser(String firstName, String lastName, String userName) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
    }

    @Override

    public void execute(){
        UserService userService = new UserServiceImpl();
        userService.saveUser(firstName, lastName, userName);
    }
}
