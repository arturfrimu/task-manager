package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class AssignUserToGroup implements Command {
    private final String groupName;
    private final String username;

    public AssignUserToGroup(String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }

    @Override
    public void execute() {
        UserService userService = new UserServiceImpl();
        userService.assignUserToGroup(username, groupName);
    }
}
