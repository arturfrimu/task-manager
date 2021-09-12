package com.stefanini.taskmanager.services.service;

import java.util.List;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;

public interface UserService {

    List<User> selectAllUsers();

    List<Task> selectUserTasks(String username);

    User saveUser(String firstName, String lastName, String userName);

    User saveUserWithTask(String firstName, String lastName, String username, String title, String description);

    void assignUserToGroup(String username, String groupName);
}

