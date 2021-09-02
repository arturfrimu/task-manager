package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class ShowUserTasks implements Command {
    private final String username;

    public ShowUserTasks(String username) {
        this.username=username;
    }

    @Override
    public void execute() throws Exception {
        UserService userService = new UserServiceImpl();
        userService.selectUserTasks(username);
    }
}