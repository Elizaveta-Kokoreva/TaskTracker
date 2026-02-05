package com.example.demo.controllers;

import com.example.demo.exeptions.TaskIncorrectDataException;
import com.example.demo.exeptions.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskIncorrectDataException.class)
    public ResponseEntity<String> handleIncorrectData(TaskIncorrectDataException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleBadJson(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().build();
    }


}

