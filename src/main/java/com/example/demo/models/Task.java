package com.example.demo.models;

public class Task {
    private Long id;
    private String name;
    private String description;
    private Status status;
    private Long assignee;

    public Task() {};

    public Task(Long id, String name, String description, Status status, Long assignee) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assignee = assignee;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getAssignee() {
        return assignee;
    }
    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

}
