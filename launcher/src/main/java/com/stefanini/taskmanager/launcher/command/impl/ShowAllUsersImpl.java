package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class ShowAllUsersImpl implements Command {
    private UserService userService;

    public void execute() throws Exception {
        userService = new UserServiceImpl();
        userService.selectAllUsers();
    }
}