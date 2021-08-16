package com.stefanini.taskmanager.services.service.impl;

import java.util.List;

import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.model.User;
import com.stefanini.taskmanager.services.service.UserService;
import com.stefanini.taskmanager.services.utils.InputValidator;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final DaoFactory daoFactory;

    static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        daoFactory = new DaoFactory();
        this.userDao = daoFactory.getUserDao();
    }

    public List<User> selectAllUsers() throws Exception {
        return userDao.selectAllUsers();
    }

    public User selectUserByUsername(String username) throws Exception {
        User user = null;
        if (InputValidator.isValid(username)){
            username = InputValidator.subStringInput(username);
            user = userDao.selectUserByUsername(username);
        }
        return user;
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
}
