package com.example.demo.repos;

import com.example.demo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasksRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Task getById(long id) {
        List<Task> tasks = this.jdbcTemplate.query(
                "select * from tasks where id = ?",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")),
                id
        );


        if (tasks.isEmpty()) {
            return null;
        }

        return tasks.get(0);

    }

    public void create(Task t) {
        this.jdbcTemplate.update(
                "insert into tasks(name, description) values (?, ?)",
                t.getName(),
                t.getDescription()
        );
    }

    public void update(Task t) {
        this.jdbcTemplate.update(
                "update tasks set name = ?, description = ? where id = ?",
                t.getName(),
                t.getDescription(),
                t.getId()
        );
    }

    public void deleteById(Long id) {
        this.jdbcTemplate.update(
                "delete from tasks where id = ?",
                id
        );
    }
}
