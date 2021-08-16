package com.stefanini.taskmanager.services.service;

import java.util.List;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;

/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.User} class for true identity
 * Interface UserService describes the behavior of the class {@link com.stefanini.taskmanager.services.service.impl.UserServiceImpl}
 */
public interface UserService {

    /** Gets all users
     * @return a list of users
     */
    List<User> selectAllUsers() throws Exception;

    /** Gets the user’s tasks
     * @param username is the parameter by which the task is searched
     */
    List<Task> selectUserTasks(String username) throws Exception;

    /** Save User identity {@link com.stefanini.taskmanager.services.model.User}
     * @param firstName indicates the user's first name
     * @param lastName indicates the user's last name
     * @param userName indicates the user's user name
     * @return void
     */
    void saveUser(String firstName, String lastName, String userName) throws Exception;

    void saveUserWithTask(String firstName, String lastName, String username, String title, String description) throws Exception;

    void assignUserToGroup(String username, String groupName);
}
