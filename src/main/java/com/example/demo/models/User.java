package com.example.demo.models;

public class User {
    private Long id;
    private String name;

    public User(){}

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }

}



