package com.stefanini.taskmanager.services.service.impl;

import static com.stefanini.taskmanager.services.utils.InputValidator.isValid;
import static com.stefanini.taskmanager.services.utils.InputValidator.subStringInput;

import com.stefanini.taskmanager.services.dao.DaoFactory;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.service.TaskService;
import org.apache.log4j.Logger;

public class TaskServiceImpl implements TaskService {
    static Logger logger = Logger.getLogger(TaskServiceImpl.class);

    private final TaskDao taskDao;

    public TaskServiceImpl() {
        DaoFactory daoFactory = new DaoFactory();
        this.taskDao = daoFactory.getTaskDao();
    }

    public void addTaskToUser(String username, String title, String description) throws Exception {
        if (isValid(username) && isValid(title) && isValid(description)) {
            username = subStringInput(username);
            title = subStringInput(title);
            description = subStringInput(description);
            taskDao.addTaskToUser(username, title, description);
        }
    }

    public void addTaskToGroup(String title, String description, String groupName) throws Exception {
        if (isValid(title) && isValid(description) && isValid(groupName)) {
            title = subStringInput(title);
            description = subStringInput(description);
            groupName = subStringInput(groupName);
            taskDao.addTaskToGroup(title, description, groupName);
        }
    }
}