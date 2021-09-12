package com.stefanini.taskmanager.services.dao;

import com.stefanini.taskmanager.services.model.Task;

public interface TaskDao {

    Task addTaskToUser(String username, Task task);

    Task addTaskToGroup(String groupName, Task task);
}
