package com.stefanini.taskmanager.services.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();

    T save(T element);
}

