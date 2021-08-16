package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.service.impl.TaskServiceImpl;

public class AddTaskToGroupImpl implements Command {
    private TaskService taskService;
    private String title;
    private String description;
    private String groupName;

    public AddTaskToGroupImpl(String title, String description, String groupName) {
        this.title = title;
        this.description = description;
        this.groupName = groupName;
    }

    public void execute() throws Exception {
        taskService = new TaskServiceImpl();
        taskService.addTaskToGroup(title, description, groupName);
    }
}