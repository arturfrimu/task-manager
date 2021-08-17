package com.stefanini.taskmanager.services.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;

import com.stefanini.taskmanager.services.dao.GroupDao;
import com.stefanini.taskmanager.services.database.DataBaseConnection;
import com.stefanini.taskmanager.services.model.Group;
import org.apache.log4j.Logger;

public class GroupDaoImpl implements GroupDao {
    static Logger logger = Logger.getLogger(GroupDaoImpl.class);

    public Group selectGroupByName(String groupName) {
        Group group = null;
        Statement statement;
        try {
            statement = DataBaseConnection.getInstance().getDBConnection().createStatement();
            String sql = "SELECT * FROM `GROUP` G WHERE G.NAME = '" + groupName + "'";
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                group = new Group();
                group.setId(res.getLong("G.ID"));
                group.setGroupName(res.getString("G.NAME"));
            }
        } catch (Exception e) {
            logger.error(e);
            logger.info(group);
        }
        return group;
    }
}