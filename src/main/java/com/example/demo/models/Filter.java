package com.example.demo.models;

public class Filter {
    private String name;
    Status status;
    private Long assignee;

    public Filter() {
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

}
