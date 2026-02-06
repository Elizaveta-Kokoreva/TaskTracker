package com.example.demo.smokeTest;

import com.example.demo.base.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

class DbSmokeTest extends BaseIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void dbSmoke_contextUp_migrationsApplied_andSelect1Works() {
        Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(one).isEqualTo(1);

        Integer usersCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        Integer tasksCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tasks", Integer.class);

        assertThat(usersCount).isEqualTo(2);
        assertThat(tasksCount).isEqualTo(3);
    }
}
