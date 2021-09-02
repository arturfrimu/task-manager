package com.stefanini.taskmanager.services.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`GROUP`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (
            name = "TASK_TO_GROUP",
            joinColumns = {@JoinColumn(name = "GROUP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TASK_ID")}
    )
    private Set<Task> groupTasks;

    @OneToMany(mappedBy="group")
    private Set<User> users;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Task> getGroupTasks() {
        return groupTasks;
    }

    public void setGroupTasks(Set<Task> groupTasks) {
        this.groupTasks = groupTasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        if (groupTasks == null)
            groupTasks = new HashSet<>();
        if (task != null)
            groupTasks.add(task);
    }

    public void addUser(User user) {
        if (users == null)
            users = new HashSet<>();
        if (user != null)
            user.setGroup(this);
        users.add(user);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
