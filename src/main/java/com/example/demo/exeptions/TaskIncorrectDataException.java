package com.example.demo.exeptions;

public class TaskIncorrectDataException extends RuntimeException{
    public TaskIncorrectDataException(String message) {
        super(message);
    }
}
