package com.example.demo.tasks;


import com.example.demo.base.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class getByIDTest extends BaseIntegrationTest{
    @Test
    void TestGetByIdReturn200_WhenTaskExists() throws Exception {
        mockMvc.perform(get("/tasks/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("a"))
                .andExpect(jsonPath("$.description").value("task"))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.assignee").value(1));
    }

    @Test
    void TestGetByIdReturn404_WhenTaskMissing() throws Exception {
        mockMvc.perform(get("/tasks/{id}", 6))
                .andExpect(status().isNotFound());

    }
}
