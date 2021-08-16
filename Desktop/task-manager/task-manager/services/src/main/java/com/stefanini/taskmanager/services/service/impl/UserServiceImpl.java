package com.stefanini.taskmanager.services.service.impl;

import java.util.List;

import com.stefanini.taskmanager.services.dao.DaoFactory;
import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.utils.InputValidator;
import org.apache.log4j.Logger;

import static com.stefanini.taskmanager.services.utils.InputValidator.isValid;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        DaoFactory daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    public List<User> selectAllUsers() throws Exception {
        return userDao.selectAllUsers();
    }

    public List<Task> selectUserTasks(String username) throws Exception {
        List<Task> tasks = null;
        if (isValid(username)) {
            username = InputValidator.subStringInput(username);
            tasks = userDao.selectUserTasks(username);
        }
        return tasks;
    }
    public void saveUser(String firstName, String lastName, String username) throws Exception {
        if (InputValidator.isValid(firstName) && InputValidator.isValid(lastName) && InputValidator.isValid(username)) {
            firstName = InputValidator.subStringInput(firstName);
            lastName = InputValidator.subStringInput(lastName);
            username = InputValidator.subStringInput(username);
            userDao.saveUser(firstName, lastName, username);
        } else {
            logger.info("User can't be saved");
        }
    }

    @Override
    public void saveUserWithTask(String firstName, String lastName, String username, String title, String description) throws Exception {
        if (InputValidator.isValid(firstName) && InputValidator.isValid(lastName) && InputValidator.isValid(username)
             && InputValidator.isValid(title) && InputValidator.isValid(description)) {
            firstName = InputValidator.subStringInput(firstName);
            lastName = InputValidator.subStringInput(lastName);
            username = InputValidator.subStringInput(username);
            title = InputValidator.subStringInput(title);
            description = InputValidator.subStringInput(description);
            userDao.saveUserWithTask(firstName,lastName,username,title,description);
        } else {
            logger.info("User can't be saved");
        }
    }

    @Override
    public void assignUserToGroup(String username, String groupName) {
        if (InputValidator.isValid(username)) {
            username = InputValidator.subStringInput(username);
            groupName = InputValidator.subStringInput(groupName);
            userDao.assignUserToGroup(username, groupName);
        } else {
            logger.info("User can't be assigned");
        }
    }
}
