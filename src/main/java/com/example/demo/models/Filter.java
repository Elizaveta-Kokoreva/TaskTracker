package com.example.demo.models;

public class Filter {
    private String name;
    private String status;
    private String assignee;
    private String logicalConnection;

    public Filter() {
    }

    public String getName() {
        return name;
    }

    public String getLogicalConnection() {
        return logicalConnection;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setLogicalConnection(String logicalConnection) {
        this.logicalConnection = logicalConnection;
    }

}
