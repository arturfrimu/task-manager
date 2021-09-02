package com.stefanini.taskmanager.services.dao;

import com.stefanini.taskmanager.services.dao.impl.*;
import org.hibernate.SessionFactory;

import static com.stefanini.taskmanager.services.database.DatabaseSessionFactory.getInstance;

public class DaoFactory {
    private final SessionFactory sessionFactory;

    public DaoFactory() {
        sessionFactory = getInstance().getSessionFactory();
    }

    public TaskDao getTaskDao() {
        return new TaskDaoImpl(sessionFactory);
    }

    public UserDao getUserDao() {
        return new UserDaoImpl(sessionFactory);
    }
}
