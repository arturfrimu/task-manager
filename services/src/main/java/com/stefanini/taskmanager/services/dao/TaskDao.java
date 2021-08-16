package com.stefanini.taskmanager.services.dao;

import java.util.List;

import com.stefanini.taskmanager.services.model.Task;

/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.Task} class for true identity
 * Interface TaskDao describes the behavior of the class {@link com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl}
 */
public interface TaskDao {

    /** Gets the users’s tasks
     * @param username is the parameter by which the task is searched
     * @return a list of user's tasks
     */
    List<Task> selectUserTasks(String username) throws Exception;

    /** Add a task to a specific user
     * @param userId indicates the parameter by which the user is searched
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     */
    void addTaskToUser(long userId, String title, String description) throws Exception;

    /** Add a task to a specific group of users
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     * @param groupId indicates the id of the group to which the task is addressed
     */
    void addTaskToGroup(String title, String description, long groupId) throws Exception;
}