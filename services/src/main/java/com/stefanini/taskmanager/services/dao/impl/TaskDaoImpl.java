package com.stefanini.taskmanager.services.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stefanini.taskmanager.services.dao.TaskDao;
import com.stefanini.taskmanager.services.database.DataBaseConnection;
import com.stefanini.taskmanager.services.model.*;

public class TaskDaoImpl implements TaskDao {
    static Logger logger = Logger.getLogger(TaskDaoImpl.class);
    private UserDaoImpl userDao = new UserDaoImpl();

    public List<Task> selectUserTasks(String username) {
        List<Task> tasks = new ArrayList<>();
        Statement statement;
        try {
            statement = DataBaseConnection.getInstance().getDBConnection().createStatement();
            String sql = "SELECT T.* FROM USERS U INNER JOIN TASK T on T.USER_ID = U.ID OR T.GROUP_ID = U.GROUP_ID WHERE U.USER_NAME = '" + username + "'";
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                Task task = new Task();
                task.setId(res.getLong("T.ID"));
                task.setTitle(res.getString("TITLE"));
                task.setDescription(res.getString("DESCRIPTION"));
                task.setGroupId(res.getLong("GROUP_ID"));
                task.setUserId(res.getLong("USER_ID"));
                tasks.add(task);
            }
            if (!tasks.isEmpty())
                logger.trace(tasks);
            else {
                logger.trace("User don't have tasks or not exists");
            }
        } catch (Exception e) {
            logger.error(e);
            logger.info(tasks);
        }
        return tasks;
    }

    public void addTaskToUser(long userId, String title, String description) {
        try {
            PreparedStatement preparedStatement = DataBaseConnection.getInstance().getDBConnection()
                    .prepareStatement("INSERT INTO TASK ( TITLE, DESCRIPTION, USER_ID ) VALUES (?, ?, ?)");

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setLong(3, userId);

            preparedStatement.executeUpdate();
            logger.trace("Task added to user with userId " + userId);
        } catch (Exception e) {
            logger.error(e);
            logger.info(userId);
        }
    }

    public void addTaskToGroup(String title, String description, long groupId) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DataBaseConnection.getInstance().getDBConnection()
                    .prepareStatement("INSERT INTO TASK (TITLE, DESCRIPTION, GROUP_ID) VALUE (?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setLong(3, groupId);

            preparedStatement.executeUpdate();
            logger.trace("Task added to group");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}