package com.stefanini.taskmanager.services.service.impl;

import java.util.List;

import com.stefanini.taskmanager.services.dao.DaoFactory;
import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import com.stefanini.taskmanager.services.service.UserService;
import org.apache.log4j.Logger;

import static com.stefanini.taskmanager.services.utils.InputValidator.isValid;
import static com.stefanini.taskmanager.services.utils.InputValidator.subStringInput;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

public class UserServiceImpl implements UserService {
    static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl() {
        DaoFactory<UserDaoImpl> daoFactory = new DaoFactory<>(UserDaoImpl.class);
        this.userDao = daoFactory.getDao();
    }

    public List<User> selectAllUsers() {
        return userDao.selectAllUsers();
    }

    public List<Task> selectUserTasks(String username) {
        List<Task> tasks = null;
        if (isValid(username)) {
            username = subStringInput(username);
            tasks = userDao.selectUserTasks(username);
        } else {
            logger.info(lineUp + "Username is not valid" + lineDown);
        }
        return tasks;
    }

    public User saveUser(String firstName, String lastName, String username) {
        User user = null;
        if (isValid(firstName) && isValid(lastName) && isValid(username)) {
            firstName = subStringInput(firstName);
            lastName = subStringInput(lastName);
            username = subStringInput(username);
            user = new User(firstName, lastName, username);
            userDao.saveUser(user);
        } else {
            logger.info(lineUp + "User can't be saved. Invalid credentials format" + lineDown);
        }
        return user;
    }

    @Override
    public User saveUserWithTask(String firstName, String lastName, String username, String title, String description) {
        User user = null;
        if (isValid(firstName) && isValid(lastName) && isValid(username) && isValid(title) && isValid(description)) {
            firstName = subStringInput(firstName);
            lastName = subStringInput(lastName);
            username = subStringInput(username);
            title = subStringInput(title);
            description = subStringInput(description);
            user = new User(firstName, lastName, username);
            try {
                user.addTask(new Task(title, description));
            } catch (Exception e) {
                logger.error(lineUp + " ---- " + e.getCause() + lineDown);
            }
            userDao.saveUserWithTask(user);
        } else {
            logger.info("User can't be saved. Invalid credentials format");
        }
        return user;

    }

    @Override
    public void assignUserToGroup(String username, String groupName) {
        if (isValid(username) && isValid(groupName)) {
            username = subStringInput(username);
            groupName = subStringInput(groupName);
            userDao.assignUserToGroup(username, groupName);
        } else {
            logger.info(lineUp + "User can't be assigned. Invalid credentials format" + lineDown);
        }
    }
}

