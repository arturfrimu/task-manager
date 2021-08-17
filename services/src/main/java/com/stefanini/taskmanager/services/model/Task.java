package com.stefanini.taskmanager.services.model;
public class Task {
    private Long id;
    private String title;
    private String description;
    private Long groupId;
    private Long userId;

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, Long groupId) {
        this.title = title;
        this.description = description;
        this.groupId = groupId;
    }

    public Task(Long id, String title, String description, Long groupId, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.groupId = groupId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + "]";
    }
}
