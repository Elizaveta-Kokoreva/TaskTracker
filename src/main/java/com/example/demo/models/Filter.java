package com.example.demo.models;

public class Filter {
    private String name;
    Status status;
    private String assignee;

    public Filter() {
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

}
