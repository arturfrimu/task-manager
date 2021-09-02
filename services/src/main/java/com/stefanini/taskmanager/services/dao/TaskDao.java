package com.stefanini.taskmanager.services.dao;
import com.stefanini.taskmanager.services.model.Task;

/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.Task} class for true identity
 * Interface TaskDao describes the behavior of the class {@link com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl}
 */
public interface TaskDao {

    /** Add a task to a specific user
     * @param username indicates the parameter by which the user is searched
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     */
    Task addTaskToUser(String username, String title, String description) throws Exception;

    /** Add a task to a specific group of users
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     * @param groupName indicates the id of the group to which the task is addressed
     */
    void addTaskToGroup(String title, String description, String groupName) throws Exception;
}
