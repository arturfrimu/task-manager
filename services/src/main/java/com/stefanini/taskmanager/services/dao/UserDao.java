package com.stefanini.taskmanager.services.dao;

import java.util.List;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;

public interface UserDao {
    List<Task> selectUserTasks(String username);

    User assignUserToGroup(String username, String groupName);
}

