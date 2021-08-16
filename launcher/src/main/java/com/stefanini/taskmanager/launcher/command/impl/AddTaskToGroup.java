package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.service.impl.TaskServiceImpl;

public class AddTaskToGroup implements Command {
    private final String title;
    private final String description;
    private final String groupName;

    public AddTaskToGroup(String title, String description, String groupName) {
        this.title = title;
        this.description = description;
        this.groupName = groupName;
    }

    @Override
    public void execute(){
        TaskService taskService = new TaskServiceImpl();
        taskService.addTaskToGroup(title, description, groupName);
    }
}