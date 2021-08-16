package com.stefanini.taskmanager.services.service;

import com.stefanini.taskmanager.services.model.Task;

public interface TaskService {

    Task addTaskToUser(String username, String title, String description);

    Task addTaskToGroup(String title, String description, String groupName);
}