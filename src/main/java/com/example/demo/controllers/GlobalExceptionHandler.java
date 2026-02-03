package com.example.demo.controllers;

import com.example.demo.exeptions.TaskIncorrectDataException;
import com.example.demo.exeptions.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskIncorrectDataException.class)
    public ResponseEntity<String> handleNotFound(TaskIncorrectDataException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.notFound().build();
    }
}

