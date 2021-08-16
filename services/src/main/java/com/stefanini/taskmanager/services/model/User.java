package com.stefanini.taskmanager.services.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Long groupId;
    private List<Task> tasks = new ArrayList<Task>();

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User() {
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(List<Task> tasks) {
        this.tasks=tasks;
    }

    public void addTask(Task task) {
        if(task != null) {
            this.tasks.add(task);
        }
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", tasks=" + tasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User us = (User) o;
            return (userName.equals(us.userName));
        } else {
            return false;
        }
    }
}