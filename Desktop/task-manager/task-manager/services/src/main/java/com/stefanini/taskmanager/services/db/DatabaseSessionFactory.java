package com.stefanini.taskmanager.services.db;

import com.stefanini.taskmanager.services.model.Group;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseSessionFactory {

    public static SessionFactory sessionFactory = null;
    private static DatabaseSessionFactory dataBaseConnection = null;

    private DatabaseSessionFactory(){}

    public static DatabaseSessionFactory getInstance() {
        if(dataBaseConnection == null) {
            dataBaseConnection = new DatabaseSessionFactory();
        }
        return dataBaseConnection;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate-config.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Group.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
