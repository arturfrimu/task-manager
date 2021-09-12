package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.service.impl.UserServiceImpl;

public class ShowAllUsers implements Command {

    public void execute(){
        UserService userService = new UserServiceImpl();
        userService.selectAllUsers();
    }
}