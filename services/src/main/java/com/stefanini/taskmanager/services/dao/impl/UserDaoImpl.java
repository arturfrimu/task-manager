package com.stefanini.taskmanager.services.dao.impl;

import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.model.Group;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, new User());
    }


    @Override
    public List<User> selectAllUsers() {
        return findAll();
    }

    @Override
    public List<Task> selectUserTasks(String userName) {
        List<Task> tasks = null;
        try(Session session = getSession()) {
            List<User> users = session.createQuery("from " + User.class.getName() + " where userName = '" + userName + "'").getResultList();
            tasks = users.get(0).getUserTasks();
            logger.trace("\n\n" + userName + "'S TASKS ARE SELECTED SUCCESSFULLY");
        } catch (Exception e){
            logger.error("\n\n" + e + "\n");
        }
        return tasks;
    }
    @Override
    public User saveUser(String firstName, String lastName, String username) {
        return save(new User(firstName, lastName, username));
    }

    @Override
    public void saveUserWithTask(String firstName, String lastName, String userName, String title, String description) {
        try (Session session = getSession()) {
            User user = new User(firstName, lastName, userName);
            user.addTask(new Task(title, description));
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                logger.trace("\n\nUSER WITH USERNAME " + userName + " WAS SAVED SUCCESSFULLY\n");
                logger.trace("\n\n" + user + "\n");
            } catch (Exception ex) {
                if (transaction != null)
                    transaction.rollback();
                throw ex;
            }
        } catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
    }

    @Override
    public void assignUserToGroup(String userName, String groupName) {
        try (Session session = getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<User> users = session.createQuery("from User where userName = '" + userName + "'").getResultList();
                User user = users.get(0);
                List<Group> groups = session.createQuery("from Group where name = '" + groupName + "'").getResultList();
                Group group = groups.get(0);
                user.setGroup(group);
                session.save(user);
                transaction.commit();
                logger.trace("\n\nUSER WITH USERNAME " + userName + " WAS ASSIGNED SUCCESSFULLY TO GROUP " + groupName + "\n\n");
            } catch (Exception ex) {
                if (transaction != null)
                    transaction.rollback();
                throw ex;
            }
        } catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
    }
}