package com.stefanini.taskmanager.services.dao.impl;

import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.model.Group;
import com.stefanini.taskmanager.services.model.Task;
import com.stefanini.taskmanager.services.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        this.element = User.class;
    }

    @Override
    public List<User> selectAllUsers() {
        return findAll();
    }

    @Override
    public List<Task> selectUserTasks(String userName) {
        List<Task> tasks = null;
        Session session = getSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            Query<User> query = session.createQuery(criteriaQuery);
            User user = query.list()
                    .stream().filter(element -> Objects.equals(element.getUserName(), userName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("User not found - " + userName));
            tasks = user.getUserTasks();

            logger.info(lineUp + "USER " + user.getUserName() + " HAS " + tasks.size() + " TASKS\n");
            tasks.forEach(System.out::println);
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger.error(lineUp + " ---- " + e + lineDown);
        } catch (Exception e) {
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return tasks;
    }

    @Override
    public User saveUser(User user) {
        return save(user);
    }

    @Override
    public User saveUserWithTask(User user) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info(lineUp + "USER " + user + " WAS SAVED SUCCESSFULLY\nHE HAS TASKS " + user.getUserTasks() + lineDown);
        } catch (ConstraintViolationException | IllegalStateException | RollbackException | IllegalArgumentException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    @Override
    public User assignUserToGroup(String userName, String groupName) {
        Session session = getSession();
        Transaction transaction = null;
        User user = null;
        Group group = null;
        try {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery1 = builder.createQuery(User.class);
            CriteriaQuery<Group> criteriaQuery2 = builder.createQuery(Group.class);

            Root<User> root1 = criteriaQuery1.from(User.class);
            Root<Group> root2 = criteriaQuery2.from(Group.class);

            criteriaQuery1.select(root1);
            criteriaQuery2.select(root2);

            Query<User> query1 = session.createQuery(criteriaQuery1);
            user = query1.list()
                    .stream().filter(element -> Objects.equals(element.getUserName(), userName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("User not found - " + userName));

            Query<Group> query2 = session.createQuery(criteriaQuery2);
            group = query2.list()
                    .stream().filter(element -> Objects.equals(element.getName(), groupName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Group not found - " + groupName));

            user.setGroup(group);
            session.save(user);

            transaction.commit();
            logger.info(lineUp + "USER " + user + " WAS ASSIGNED TO GROUP " + group + " SUCCESSFULLY" + lineDown);
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
        return user;
    }
}
