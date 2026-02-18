package com.example.demo.tasks;

import com.example.demo.base.BaseIntegrationTest;
import com.example.demo.models.Status;
import com.example.demo.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

        List<Task> tasks = this.jdbcTemplate.query(
                "SELECT * FROM tasks WHERE id = 1",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        rs.getLong("assignee")));

        Task task = tasks.getFirst();

        assertEquals("updated", task.getName());
        assertEquals("updated desc", task.getDescription());
        assertEquals(Status.DONE, task.getStatus());
        assertEquals(2, task.getAssignee());
    }

    @Test
    void TestPutTaskReturn404_WhenTaskMissing() throws Exception {
        String body = """
                {
                  "name": "updated",
                  "description": "updated desc",
                  "status": "DONE",
                  "assignee": 2
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void TestPutTaskReturn204_WhenStatusIsNull_SetToNEW() throws Exception {
        String body = """
                {
                  "name": "updated",
                  "description": "updated desc",
                  "assignee": 2
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());

        List<Task> tasks = this.jdbcTemplate.query(
                "SELECT * FROM tasks WHERE id = 1",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        rs.getLong("assignee")));

        Task task = tasks.getFirst();

        assertEquals(Status.NEW, task.getStatus());

    }

    @Test
    void TestPutTaskReturn400_WhenNameIsBlank() throws Exception {
        String body = """
                {
                  "name": " ",
                  "description": "updated desc",
                  "status": "DONE",
                  "assignee": 2
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void TestPutTaskReturn400_WhenNameIsNull() throws Exception {
        String body = """
                {
                  "description": "updated desc",
                  "status": "DONE",
                  "assignee": 2
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void TestPutTaskReturn400_WhenAssigneeDoesNotExist() throws Exception {
        String body = """
                {
                  "name": "updated",
                  "description": "updated desc",
                  "status": "DONE",
                  "assignee": 3
                }
                """;

        mockMvc.perform(put("/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}
