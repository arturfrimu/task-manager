package com.stefanini.taskmanager.launcher.command.impl;

import com.stefanini.taskmanager.launcher.command.Command;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.service.impl.TaskServiceImpl;

public class ShowUserTasksImpl implements Command {
    private TaskService taskService;
    private String username;

    public ShowUserTasksImpl(String username) {
        this.username=username;
    }

    @Override
    public void execute() throws Exception {
        this.taskService = new TaskServiceImpl();
        taskService.selectUserTasks(username);
    }
}