package com.stefanini.taskmanager.services.dao.impl;

import org.apache.log4j.Logger;
import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Objects;

import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {
    static Logger logger = Logger.getLogger(TaskDaoImpl.class);

    public TaskDaoImpl() {
        this.element = Task.class;
    }

    @Override
    public Task addTaskToUser(String userName, Task task) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            Query<User> query = session.createQuery(criteriaQuery);
            User user = query.list()
                    .stream().filter(element -> Objects.equals(element.getUserName(), userName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("User not found - " + userName));

            user.addTask(task);
            session.save(user);
            transaction.commit();
            logger.info(lineUp + "TASK " + task + " WAS ADDED SUCCESSFULLY TO USER " + user.getUserName() + lineDown);
        } catch (IllegalStateException | RollbackException | IllegalArgumentException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(lineUp + " ---- " + e + lineDown);
        } catch (Exception e) {
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return task;
    }

    @Override
    public Task addTaskToGroup(String groupName, Task task) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery.select(root);

            Query<Group> query = session.createQuery(criteriaQuery);
            Group group = query.list()
                    .stream().filter(element -> Objects.equals(element.getName(), groupName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Group not found - " + groupName));

            group.addTask(task);
            session.save(group);

            transaction.commit();
            logger.info(lineUp + "TASK " + task + " WAS ADDED SUCCESSFULLY TO GROUP " + group.getName() + lineDown);
        } catch (IllegalStateException | RollbackException | IllegalArgumentException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(lineUp + " ---- " + e + lineDown);
        } catch (Exception e) {
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return task;
    }
}