package com.example.demo.repos;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UsersRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getById(long id) {
        List<User> users = this.jdbcTemplate.query(
                "select * from users where id = ?",
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("name")),
                id
        );

        if (users.isEmpty()) {
            return null;
        }

        return users.getFirst();
    }
}
