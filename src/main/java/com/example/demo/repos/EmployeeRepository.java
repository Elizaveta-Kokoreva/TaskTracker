package com.example.demo.repos;

import com.example.demo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public class EmployeeRepository {

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

    public void create(Task e) {
        this.jdbcTemplate.update(
                "insert into employees(id, name, role) values (?, ?, ?)",
                e.getId(),
                e.getName(),
                e.getDescription()
        );
    }
}
