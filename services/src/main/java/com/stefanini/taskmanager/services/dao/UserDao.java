package com.stefanini.taskmanager.services.dao;

import java.util.List;

import com.stefanini.taskmanager.services.model.User;

/**
 * @author Artur Frimu
 * Please see the {@link com.stefanini.taskmanager.services.model.User} class for true identity
 * Interface UserDAO describes the behavior of the class {@link com.stefanini.taskmanager.services.dao.impl.UserDaoImpl}
 */
public interface UserDao {

    /** Gets all users
     * @return a list of users
     */
    List<User> selectAllUsers() throws Exception;

    /** Gets a specific user by username
     * @param username indicates the parameter by which the user is searched
     * @return User identity {@link com.stefanini.taskmanager.services.model.User}
     */
    User selectUserByUsername(String username) throws Exception;

    /** Save User identity {@link com.stefanini.taskmanager.services.model.User}
     * @param firstName indicates the user's first name
     * @param lastName indicates the user's last name
     * @param userName indicates the user's user name
     * @return void
     */
    void saveUser(String firstName, String lastName, String userName) throws Exception;
}
