package com.stefanini.taskmanager.services.dao;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao<T> {
    Session getSession();

    List<T> findAll();

    T save(T element);
}
