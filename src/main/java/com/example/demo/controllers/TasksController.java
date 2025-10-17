package com.example.demo.controllers;

import com.example.demo.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tasks")

public class TasksController {
    private Map<Long, Task> Tasks;

    public TasksController() {
        this.Tasks = new HashMap<>();
    }

    public Map<Long, Task> getTasks() {
        return this.Tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById (@PathVariable Long id) {
        if(!this.Tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Task t = this.Tasks.get(id);

        return ResponseEntity.ok().body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(!this.Tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Tasks.remove(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putById (@RequestBody Task updatedTask) {
        Long id = updatedTask.getId();
        if (!this.Tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Tasks.put(id, updatedTask);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task newTask) {
        if(newTask.getId() == null || newTask.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long id = newTask.getId();
        if(this.Tasks.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        this.Tasks.put(id, newTask);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }






}
