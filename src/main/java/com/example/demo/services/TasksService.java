package com.example.demo.services;

import com.example.demo.models.Filter;
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

    public Boolean createTask(Task newTask) {
        if (newTask.getName() == null) {
            return Boolean.FALSE;
        }

        if (newTask.getStatus() == null) {
            newTask.setStatus("New");
        }

        if (!Objects.equals(newTask.getStatus(), "new") &&
                !Objects.equals(newTask.getStatus(), "in progress") &&
                !Objects.equals(newTask.getStatus(), "done")) {
            return Boolean.FALSE;
        }

        if (tasksRepository.getById(newTask.getAssignee()) != null &&
                usersService.getById(newTask.getAssignee()) == null) {
            return Boolean.FALSE;
        }

//        if (!getByFilter().isEmpty()) {
//            return Boolean.FALSE;
//        }

        tasksRepository.create(newTask);

        return Boolean.TRUE;
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

    public Boolean deleteById(Long id) {

        if (getById(id) != null) {
            tasksRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    public Boolean putById (Task updatedTask) {

        Long id = updatedTask.getId();
        if (tasksRepository.getById(id) == null) {
            return Boolean.FALSE;
        }

        if (updatedTask.getName() == null) {
            return Boolean.FALSE;
        }

        if(updatedTask.getStatus() == null) {
            updatedTask.setStatus("new");
        }

        if (!Objects.equals(updatedTask.getStatus(), "new") &&
                !Objects.equals(updatedTask.getStatus(), "in progress") &&
                !Objects.equals(updatedTask.getStatus(), "done")) {
            return Boolean.FALSE;
        }

        if (tasksRepository.getById(updatedTask.getAssignee()) != null &&
                usersService.getById(updatedTask.getAssignee()) == null) {
            return Boolean.FALSE;
        }

        tasksRepository.update(updatedTask);

        return Boolean.TRUE;
    }
}
