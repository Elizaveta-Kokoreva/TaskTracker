package com.example.demo.controllers;

import com.example.demo.models.Filter;
import com.example.demo.models.Task;
import com.example.demo.repos.TasksRepository;
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

        if(t == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(t);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task newTask) {
        Boolean isCreate = tasksService.createTask(newTask);

        if(!isCreate) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping("/getTaskByFilter")
//    public ResponseEntity<List<Task>> getTaskByFilter(@RequestBody Filter newFilter) {
//        List<Task> t = TasksRepository.getByFilter(newFilter);
//
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        Boolean isDelete = tasksService.deleteById(id);
        if (isDelete) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putById (@RequestBody Task updatedTask) {

        Boolean isPut = tasksService.putById(updatedTask);
        if (isPut) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }








}
