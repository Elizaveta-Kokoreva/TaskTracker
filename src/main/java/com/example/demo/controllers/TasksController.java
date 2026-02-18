package com.example.demo.controllers;

import com.example.demo.models.Filter;
import com.example.demo.models.Task;
import com.example.demo.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")

public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById (@PathVariable Long id) {
        Task t = tasksService.getById(id);

        return ResponseEntity.ok().body(t);
    }

    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody Task newTask) {
        Long id = tasksService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/getTaskByFilter")
    public ResponseEntity<List<Task>> getTaskByFilter(@RequestBody Filter filter) {
        List<Task> t = tasksService.getTaskByFilter(filter);
        return ResponseEntity.ok().body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putById (@RequestBody Task updatedTask) {

        tasksService.updateById(updatedTask);
        return ResponseEntity.noContent().build();
    }








}
