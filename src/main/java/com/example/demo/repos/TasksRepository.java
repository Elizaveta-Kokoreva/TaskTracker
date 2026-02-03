package com.example.demo.repos;

import com.example.demo.models.Filter;
import com.example.demo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                        rs.getString("description"),
                        Task.Status.valueOf(rs.getString("status")),
                        rs.getLong("assignee")),
                id
        );

        if (tasks.isEmpty()) {
            return null;
        }

        return tasks.getFirst();
    }

    public Long create(Task t) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO tasks (name, description, status, assignee) " +
                            "VALUES (?, ?, ?, ?) RETURNING id",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getStatus());
            ps.setLong(4, t.getAssignee());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

//    public List<Task> getByFilter(Filter filter) {
//        this.jdbcTemplate.query("select * from tasks where id = ?"
//
//        );
//    }


    public void update(Task t) {
        this.jdbcTemplate.update(
                "update tasks set name = ?, description = ? where id = ?",
                t.getName(),
                t.getDescription(),
                t.getId()
        );
    }
}

