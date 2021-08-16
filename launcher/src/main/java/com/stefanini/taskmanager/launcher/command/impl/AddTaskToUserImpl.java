package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.service.impl.TaskServiceImpl;

public class AddTaskToUserImpl implements Command {
    private TaskService taskService;
    private String username;
    private String title;
    private String description;

    public AddTaskToUserImpl(String username, String title, String description) {
        this.username=username;
        this.title=title;
        this.description=description;
    }

    @Override
    public void execute() throws Exception {
        this.taskService = new TaskServiceImpl();
        taskService.addTaskToUser(username , title, description);
    }
}
