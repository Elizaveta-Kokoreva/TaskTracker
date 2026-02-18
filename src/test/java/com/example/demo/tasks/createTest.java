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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class createTest extends BaseIntegrationTest{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void TestCreateTaskReturn201_WhenValidBody_AndDbGeneratesId() throws Exception {
        JdbcTemplate jdbc;

        String body = """
                {
                  "name": "new task",
                  "description": "desc",
                  "status": "DONE",
                  "assignee": 1
                }
                """;

        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        long id = Long.parseLong(response);

        List<Task> tasks = this.jdbcTemplate.query(
                "SELECT * FROM tasks WHERE id = ?",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        rs.getLong("assignee")),
                id);

        Task task = tasks.getFirst();
        assertEquals("new task", task.getName());
        assertEquals("desc", task.getDescription());
        assertEquals(Status.DONE, task.getStatus());
        assertEquals(1, task.getAssignee());
    }


    @Test
    void TestCreateTaskReturn400_WhenNameIsBlank() throws Exception {
        String body = """
                {
                  "name": " ",
                  "description": "desc",
                  "status": "DONE",
                  "assignee": 1
                }
                """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void TestCreateTaskReturn400_WhenNameIsNull() throws Exception {
        String body = """
                {
                  "description": "desc",
                  "status": "DONE",
                  "assignee": 1
                }
                """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void TestCreateTaskReturn201_WhenStatusIsNull_SetToNEW() throws Exception {
        JdbcTemplate jdbc;

        String body = """
                {
                  "name": "new task",
                  "description": "desc",
                  "assignee": 1
                }
                """;

        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        long id = Long.parseLong(response);

        List<Task> tasks = this.jdbcTemplate.query(
                "SELECT * FROM tasks WHERE id = ?",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        rs.getLong("assignee")),
                id);

        Task task = tasks.getFirst();
        assertEquals(Status.NEW, task.getStatus());
    }

    @Test
    void TestCreateTaskReturn400_WhenAssigneeDoesNotExist() throws Exception {
        String body = """
                {
                  "name": "new task"
                  "description": "desc",
                  "status": "DONE",
                  "assignee": 3
                }
                """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }



}
