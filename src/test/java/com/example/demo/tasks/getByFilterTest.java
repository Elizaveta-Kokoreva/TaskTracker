package com.example.demo.tasks;

import com.example.demo.base.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class getByFilterTest extends BaseIntegrationTest {
    @Test
    void TestGetTaskByFilterReturn200_WhenFilterIsEmpty_ReturnAll() throws Exception {
        String emptyFilter = "{}";

        mockMvc.perform(post("/tasks/getTaskByFilter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(emptyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // предполагаем, что возвращается массив задач
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));
    }

}
