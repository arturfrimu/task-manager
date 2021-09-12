package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.service.impl.TaskServiceImpl;

public class AddTaskToUser implements Command {
    private final String username;
    private final String title;
    private final String description;

    public AddTaskToUser(String username, String title, String description) {
        this.username=username;
        this.title=title;
        this.description=description;
    }

    @Override

    public void execute(){
        TaskService taskService = new TaskServiceImpl();
        taskService.addTaskToUser(username , title, description);
    }
}
