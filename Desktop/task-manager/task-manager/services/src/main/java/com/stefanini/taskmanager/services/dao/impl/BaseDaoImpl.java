package com.stefanini.taskmanager.services.dao.impl;

import com.stefanini.taskmanager.services.dao.BaseDao;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
    private final SessionFactory sessionFactory;
    private final T entity;

    public BaseDaoImpl(SessionFactory sessionFactory, T entity) {
        this.sessionFactory = sessionFactory;
        this.entity = entity;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<T> findAll() {
        List<T> elements = null;
        try (Session session = getSession()) {
            elements = session.createQuery("from " + entity.getClass().getName()).getResultList();
            System.out.println("\n");
            for (T element:elements) {
                logger.trace(element);
            }
            logger.trace("\n\nSELECTED ALL " + entity.getClass().getName() + " OBJECTS\n");
        } catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
        return elements;
    }
    @Override
    public T save(T element) {
        try (Session session = getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(element);
                transaction.commit();
                logger.trace("\n\nELEMENT " + element + " WAS SAVED SUCCESSFULLY \n");
            } catch (Exception ex){
                if (transaction != null)
                    transaction.rollback();
                throw ex;
            }
        } catch (Exception e) {
            logger.error("\n\n" + e + "\n\n");
        }
        return element;
    }
}