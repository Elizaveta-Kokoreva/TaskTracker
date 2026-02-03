package com.example.demo.services;

import com.example.demo.exeptions.TaskIncorrectDataException;
import com.example.demo.exeptions.TaskNotFoundException;
import com.example.demo.models.Filter;
import com.example.demo.models.Status;
import com.example.demo.models.Task;
import com.example.demo.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UsersService usersService;

    public Task getById(Long id) {
        return tasksRepository.getById(id);
    }

    public Long createTask(Task newTask) {
        if (newTask.getName() == null) {
            throw new TaskIncorrectDataException("Name is empty");
        }

        if (newTask.getStatus() == null) {
            newTask.setStatus(Status.NEW);
        }

        if (!Objects.equals(newTask.getStatus(), "new") &&
                !Objects.equals(newTask.getStatus(), "in progress") &&
                !Objects.equals(newTask.getStatus(), "done")) {
            throw new TaskIncorrectDataException("Wrong status");
        }

        if (newTask.getAssignee() != null &&
                usersService.getById(newTask.getAssignee()) == null) {
            throw new TaskIncorrectDataException("User Not Found");
        }

        return tasksRepository.create(newTask);
    }

//    public List<Task> getTaskByFilter(Filter filter) {
//
//        if (!Objects.equals(filter.getLogicalConnection(), "AND") &&
//                !Objects.equals(filter.getLogicalConnection(), "OR")) {
//
//        }
//        List<Task> t = tasksService.getByFilter(newFilter);
//
//        return t;
//    }

    public void updateById (Task updatedTask) {

        Long id = updatedTask.getId();
        if (tasksRepository.getById(id) == null) {
            throw new TaskNotFoundException();
        }

        if (updatedTask.getName() == null) {
            throw new TaskIncorrectDataException("Name is empty");
        }

        if(updatedTask.getStatus() == null) {
            updatedTask.setStatus("new");
        }

        if (!Objects.equals(updatedTask.getStatus(), "new") &&
                !Objects.equals(updatedTask.getStatus(), "in progress") &&
                !Objects.equals(updatedTask.getStatus(), "done")) {
            throw new TaskIncorrectDataException("Wrong status");
        }

        if (updatedTask.getAssignee() != null &&
                usersService.getById(updatedTask.getAssignee()) == null) {
            throw new TaskIncorrectDataException("User Not Found");
        }

        tasksRepository.update(updatedTask);
    }
}
