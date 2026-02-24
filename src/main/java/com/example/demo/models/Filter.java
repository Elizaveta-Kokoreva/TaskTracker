package com.example.demo.models;

public class Filter {
    private String name;
    Status status;
    private Long assigneeID;

    public Filter() {
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Long getAssigneeID() {
        return assigneeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssigneeID(Long assigneeID) {
        this.assigneeID = assigneeID;
    }

}
