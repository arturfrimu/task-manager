package com.stefanini.taskmanager.services.service.impl;

import com.stefanini.taskmanager.services.dao.GroupDao;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.dao.impl.GroupDaoImpl;
import com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.services.dao.impl.UserDaoImpl;

public class DaoFactory {
    public TaskDao getTaskDao() {
        return new TaskDaoImpl();
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public GroupDao getGroupDao() {
        return new GroupDaoImpl();
    }
}
