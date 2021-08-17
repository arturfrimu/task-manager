package com.stefanini.taskmanager.services.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stefanini.taskmanager.services.dao.UserDao;
import com.stefanini.taskmanager.services.database.DataBaseConnection;
import com.stefanini.taskmanager.services.model.*;

public class UserDaoImpl implements UserDao {
    static Logger logger = Logger.getLogger(UserDaoImpl.class);

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<User>();
        Statement statement;
        try {
            statement = DataBaseConnection.getInstance().getDBConnection().createStatement();
            String sql = "SELECT * FROM USERS U LEFT JOIN TASK T on T.USER_ID = U.ID OR T.GROUP_ID = U.GROUP_ID ORDER BY U.ID ASC";
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                User user = null;
                long userId = res.getLong("U.ID");
                long taskId = res.getLong("T.ID");
                user = users.stream().filter(element -> element.getId().equals(userId)).findAny().orElse(null);
                if(user==null)	{
                    user = new User();
                    user.setId(res.getLong("U.ID"));
                    user.setFirstName(res.getString("FIRST_NAME"));
                    user.setLastName(res.getString("LAST_NAME"));
                    user.setUserName(res.getString("USER_NAME"));
                    user.setGroupId(res.getLong("GROUP_ID"));
                    users.add(user);
                }
                if(user!=null && taskId!=0) {
                    Task task = new Task();
                    task.setId(res.getLong("T.ID"));
                    task.setTitle(res.getString("TITLE"));
                    task.setDescription(res.getString("DESCRIPTION"));
                    task.setGroupId(res.getLong("GROUP_ID"));
                    task.setUserId(res.getLong("USER_ID"));
                    user.addTask(task);
                }
            }
            logger.trace(users);
        } catch (Exception e) {
            logger.error(e);
            logger.info(users);
        }
        return users;
    }

    public User selectUserByUsername(String username)  {
        User user = new User();
        Statement statement;
        try {
            statement = DataBaseConnection.getInstance().getDBConnection().createStatement();
            String sql = "SELECT * FROM USERS U LEFT JOIN TASK T on T.USER_ID = U.ID OR T.GROUP_ID = U.GROUP_ID WHERE U.USER_NAME = '" + username + "'";
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                user.setId(res.getLong("U.ID"));
                user.setFirstName(res.getString("FIRST_NAME"));
                user.setLastName(res.getString("LAST_NAME"));
                user.setUserName(res.getString("USER_NAME"));
                user.setGroupId(res.getLong("GROUP_ID"));

                Task task = new Task();

                task.setId(res.getLong("T.ID"));
                task.setTitle(res.getString("TITLE"));
                task.setDescription(res.getString("DESCRIPTION"));
                task.setGroupId(res.getLong("GROUP_ID"));
                task.setUserId(res.getLong("USER_ID"));
                user.addTask(task);
            }
        } catch (Exception e) {
            logger.error(e);
            logger.info(user);
        }
        return user;
    }

    public void saveUser(String firstName, String lastName, String username) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DataBaseConnection.getInstance().getDBConnection()
                    .prepareStatement("INSERT INTO USERS (FIRST_NAME, LAST_NAME, USER_NAME) VALUES(?, ?, ?)");

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);

            preparedStatement.executeUpdate();
            logger.trace("User by username " + username + " was inserted");
        } catch (SQLException e) {
            logger.error(e);
            logger.info("Can't save user because username ''" + username + "'' already exists into database or username is null");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
