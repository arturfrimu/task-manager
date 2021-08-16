package com.stefanini.taskmanager.services.dao;

import java.util.List;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;

public interface UserDao {

    List<User> selectAllUsers();

    List<Task> selectUserTasks(String username);

    User saveUser(User user);

    User saveUserWithTask(User user);

    User assignUserToGroup(String username, String groupName);
}