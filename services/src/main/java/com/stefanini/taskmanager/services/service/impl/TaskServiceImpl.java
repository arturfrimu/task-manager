package com.stefanini.taskmanager.services.service.impl;

import static com.stefanini.taskmanager.services.utils.InputValidator.isValid;
import static com.stefanini.taskmanager.services.utils.InputValidator.subStringInput;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

import com.stefanini.taskmanager.services.dao.DaoFactory;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.service.TaskService;
import org.apache.log4j.Logger;

public class TaskServiceImpl implements TaskService {
    static Logger logger = Logger.getLogger(TaskServiceImpl.class);
    private final TaskDaoImpl taskDao;

    public TaskServiceImpl() {
        DaoFactory<TaskDaoImpl> daoFactory = new DaoFactory<>(TaskDaoImpl.class);
        this.taskDao = daoFactory.getDao();
    }

    public Task addTaskToUser(String username, String title, String description) {
        Task task = null;

        if (isValid(username) && isValid(title) && isValid(description)) {
            username = subStringInput(username);
            title = subStringInput(title);
            description = subStringInput(description);
            task = new Task(title, description);
            taskDao.addTaskToUser(username, task);
        } else {
            logger.info(lineUp + "Task can't be added. Invalid credentials format" + lineDown);
        }
        return task;
    }

    public Task addTaskToGroup(String title, String description, String groupName) {
        Task task = null;

        if (isValid(title) && isValid(description) && isValid(groupName)) {
            title = subStringInput(title);
            description = subStringInput(description);
            groupName = subStringInput(groupName);
            task = new Task(title, description);
            taskDao.addTaskToGroup(groupName, task);
        } else {
            logger.info(lineUp + "Task can't be added. Invalid credentials format" + lineDown);
        }
        return task;

    }
}