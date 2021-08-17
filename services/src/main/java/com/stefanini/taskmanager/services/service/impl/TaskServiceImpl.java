package com.stefanini.taskmanager.services.service.impl;

import com.stefanini.taskmanager.services.dao.GroupDao;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.model.Group;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import com.stefanini.taskmanager.services.service.TaskService;
import com.stefanini.taskmanager.services.utils.InputValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final DaoFactory daoFactory;
    private final TaskDao taskDao;
    private final UserDao userDao;
    private final GroupDao groupDao;

    static Logger logger = Logger.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl() {
        daoFactory = new DaoFactory();
        this.taskDao = daoFactory.getTaskDao();
        this.userDao = daoFactory.getUserDao();
        this.groupDao = daoFactory.getGroupDao();
    }

    public List<Task> selectUserTasks(String username) throws Exception {
        List<Task> tasks = null;
        if (InputValidator.isValid(username)) {
            username = InputValidator.subStringInput(username);
            tasks = taskDao.selectUserTasks(username);
        }
        return tasks;
    }

    public void addTaskToUser(String username, String title, String description) throws Exception {
        if (InputValidator.isValid(username) && InputValidator.isValid(title) && InputValidator.isValid(description)) {
            username = InputValidator.subStringInput(username);
            title = InputValidator.subStringInput(title);
            description = InputValidator.subStringInput(description);
            User user = userDao.selectUserByUsername(username);
            taskDao.addTaskToUser(user.getId(), title, description);
        } else {
            logger.info("Task can't be added to user " + username);
        }
    }

    public void addTaskToGroup(String title, String description, String groupName) throws Exception {
        if (InputValidator.isValid(title) && InputValidator.isValid(description) && InputValidator.isValid(groupName)) {
            title = InputValidator.subStringInput(title);
            description = InputValidator.subStringInput(description);
            groupName = InputValidator.subStringInput(groupName);

            Group group = groupDao.selectGroupByName(groupName);
            taskDao.addTaskToGroup(title, description, group.getId());
        } else {
            logger.info("Task can't be added to group " + groupName);
        }
    }
}