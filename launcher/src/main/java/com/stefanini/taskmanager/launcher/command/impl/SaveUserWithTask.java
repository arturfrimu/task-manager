package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class SaveUserWithTask implements Command {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String title;
    private final String description;

    public SaveUserWithTask(String firstName, String lastName, String userName, String title, String description) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.title=title;
        this.description=description;
    }

    @Override
    public void execute(){
        UserService userService = new UserServiceImpl();
        userService.saveUserWithTask(firstName, lastName, userName, title, description);
    }
}
