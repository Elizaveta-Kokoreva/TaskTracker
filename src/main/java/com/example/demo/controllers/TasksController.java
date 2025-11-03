package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")

public class TasksController {
    @Autowired
    private TasksRepository taskRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById (@PathVariable Long id) {
        Task t = taskRepository.getById(id);

        if(t == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putById (@RequestBody Task updatedTask) {
        Long id = updatedTask.getId();
        if (taskRepository.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.update(updatedTask);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task newTask) {
        if(newTask.getId() == null || newTask.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        taskRepository.create(newTask);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }






}
