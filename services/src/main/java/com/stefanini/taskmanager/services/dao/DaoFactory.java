package com.stefanini.taskmanager.services.dao;

import com.stefanini.taskmanager.services.dao.impl.*;

public class DaoFactory<T extends BaseDaoImpl> {
    private final Class<T> clazz;

    public DaoFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getDao() {
        try {
            return clazz.newInstance();
        } catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
       return null;
    }
}
