package com.stefanini.taskmanager.services.dao;

import com.stefanini.taskmanager.services.model.Group;
/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.Group} class for true identity
 * Interface GroupDao describes the behavior of the class {@link com.stefanini.taskmanager.services.dao.impl.GroupDaoImpl}
 */
public interface GroupDao {
    /** Gets the group by group name
     * @param groupName is the parameter by which the group is searched
     */
    Group selectGroupByName(String groupName);
}
