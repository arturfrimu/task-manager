package com.stefanini.taskmanager.services.service;

/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.Task} class for true identity
 * Interface TaskService describes the behavior of the class {@link com.stefanini.taskmanager.services.service.impl.TaskServiceImpl}
 */
public interface TaskService {

    /** Add a task to a specific user
     * @param username indicates the parameter by which the user is searched
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     */
    void addTaskToUser(String username, String title, String description) throws Exception;

    /** Add a task to a specific group
     * @param title indicates the title of the task
     * @param description indicates the description of the task
     * @param groupName indicates the name of the group to which the task is addressed
     */
    void addTaskToGroup(String title, String description, String groupName) throws Exception;
}
