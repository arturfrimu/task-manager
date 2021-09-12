package com.stefanini.taskmanager.services.dao.impl;

import com.stefanini.taskmanager.services.dao.BaseDao;
import com.stefanini.taskmanager.services.database.DatabaseSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

public class BaseDaoImpl<T> implements BaseDao<T> {
    static final Logger logger = Logger.getLogger(BaseDaoImpl.class);

    protected SessionFactory sessionFactory;
    protected Class<T> element;

    public BaseDaoImpl() {
        this.sessionFactory = DatabaseSessionFactory.getInstance().getSessionFactory();
    }

    protected Session getSession() {
        try {
            return this.sessionFactory.openSession();
        } catch (HibernateException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> list = null;
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(element);
            Root<T> root = criteriaQuery.from(element);
            criteriaQuery.select(root);

            Query<T> query = session.createQuery(criteriaQuery);
            list = query.list();

            transaction.commit();
            logger.info(lineUp + "ALL ELEMENTS ARE SELECTED SUCCESSFULLY\n");
            list.forEach(System.out::println);
        } catch (IllegalStateException | RollbackException | IllegalArgumentException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    @Override
    public T save(T element) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            logger.info(lineUp + element + " WAS SAVED SUCCESSFULLY" + lineDown);
        } catch (ConstraintViolationException | IllegalStateException | RollbackException | IllegalArgumentException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            if (session != null)
                session.close();
        }
        return element;
    }
}
