package com.example.demo.services;

import com.example.demo.exeptions.TaskIncorrectDataException;
import com.example.demo.exeptions.TaskNotFoundException;
import com.example.demo.models.Filter;
import com.example.demo.models.Status;
import com.example.demo.models.Task;
import com.example.demo.repos.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UsersService usersService;

    public Task getById(Long id) {
        Task task =  tasksRepository.getById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task not found");
        }
        return task;
    }

    public Long createTask(Task newTask) {
        if (newTask.getName() == null) {
            throw new TaskIncorrectDataException("Name is empty");
        }

        if (newTask.getStatus() == null) {
            newTask.setStatus(Status.NEW);
        }

        if (newTask.getAssignee() != null &&
                usersService.getById(newTask.getAssignee()) == null) {
            throw new TaskIncorrectDataException("User Not Found");
        }

        return tasksRepository.create(newTask);
    }

    public List<Task> getTaskByFilter(Filter filter) {

        List<Task> t = tasksRepository.getByFilter(filter);
        if (t.isEmpty()) {
            throw new TaskNotFoundException("Tasks not found");
        }
        return t;
    }

    public void updateById (Task updatedTask) {

        Long id = updatedTask.getId();
        if (tasksRepository.getById(id) == null) {
            throw new TaskNotFoundException("Task not found");
        }

        if (updatedTask.getName() == null) {
            throw new TaskIncorrectDataException("Name is empty");
        }

        if(updatedTask.getStatus() == null) {
            updatedTask.setStatus(Status.NEW);
        }

        if (updatedTask.getAssignee() != null &&
                usersService.getById(updatedTask.getAssignee()) == null) {
            throw new TaskIncorrectDataException("User Not Found");
        }

        tasksRepository.update(updatedTask);
    }
}
