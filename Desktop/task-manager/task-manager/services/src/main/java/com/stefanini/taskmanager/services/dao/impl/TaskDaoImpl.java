package com.stefanini.taskmanager.services.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {
    static Logger logger = Logger.getLogger(TaskDaoImpl.class);

    public TaskDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, new Task());
    }

    @Override
    public Task addTaskToUser(String userName, String title, String description) {
        try(Session session = getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<User> users = session.createQuery("from " + User.class.getName() + " where userName = '" + userName + "'").getResultList();
                User user = users.get(0);
                user.addTask(new Task(title, description));
                session.save(user);
                transaction.commit();
                logger.trace("\n\nTASK ADDED SUCCESSFULLY TO USER: " + user.getUserName() + "\n");
            } catch (Exception ex){
                if (transaction != null)
                    transaction.rollback();
                throw ex;
            }
        }  catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
        return new Task(title, description);
    }

    @Override
    public void addTaskToGroup(String title, String description, String groupName) {
        try(Session session = getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Group> groups = session.createQuery("from " + Group.class.getName() + " where name = '" + groupName + "'").getResultList();
                Group group = groups.get(0);
                group.addTask(new Task(title, description));
                session.save(group);
                transaction.commit();
                logger.trace("\n\nTASK ADDED SUCCESSFULLY TO GROUP: " + group.getName() + "\n");
            } catch (Exception ex){
                if (transaction != null)
                    transaction.rollback();
                throw ex;
            }
        }  catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
    }
}