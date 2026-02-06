package com.example.demo.tasks;

import com.example.demo.base.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class updateTest extends BaseIntegrationTest{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void TestPutTaskReturn204_WhenTaskExists_AndUpdated() throws Exception {
        String body = """
                {
                  "name": "updated",
                  "description": "updated desc",
                  "status": "DONE",
                  "assignee": 2
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());

        // Проверяем в БД, что реально обновилось
        String name = jdbcTemplate.queryForObject(
                "SELECT name FROM tasks WHERE id = 1",
                String.class
        );
        String description = jdbcTemplate.queryForObject(
                "SELECT description FROM tasks WHERE id = 1",
                String.class
        );
        String status = jdbcTemplate.queryForObject(
                "SELECT status FROM tasks WHERE id = 1",
                String.class
        );
        Integer assignee = jdbcTemplate.queryForObject(
                "SELECT assignee FROM tasks WHERE id = 1",
                Integer.class
        );

        assertThat(name).isEqualTo("updated");
        assertThat(description).isEqualTo("updated desc");
        assertThat(status).isEqualTo("DONE");
        assertThat(assignee).isEqualTo(2);
    }
}
