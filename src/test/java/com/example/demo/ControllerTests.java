//package com.example.demo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // подключает MockMvc
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // get(), post() и т.п.
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;   // проверки (status, jsonPath)
//import org.junit.jupiter.api.BeforeEach;
//import com.example.demo.models.Task;
//import com.example.demo.controllers.TasksController;
//import org.springframework.http.MediaType;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//
//public class ControllerTests {
//    @Test
//    void contextLoads() {
//    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TasksController tasksController;
//
//    @BeforeEach
//    void setUp() {
//        tasksController.getTasks().clear(); // очищаем карту перед каждым тестом
//        tasksController.getTasks().put(1L, new Task(1L, "Demo", "QA"));
//    }
//
//    void TestGetByIdReturn200() throws Exception {
//        mockMvc.perform(get("/tasks/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Demo"))
//                .andExpect(jsonPath("$.description").value("QA"));
//    }
//
//    @Test
//    void TestGetByIdReturn404() throws Exception {
//        mockMvc.perform(get("/tasks/2")
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void cTestPostReturn201() throws Exception {
////        String newTaskJson = """
////        {
////          "id": 2,
////          "name": "New Task",
////          "description": "Write tests"
////        }
////    """;
//        Task t = new Task(2L, "new", "new");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(t);
//
//        mockMvc.perform(post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                        .andExpect(status().isCreated());
//    }
//
//    @Test
//    void TestPostReturn400() throws Exception {
//        String json = """
//            {
//              "description": "Missing id and name"
//            }
//        """;
//
//        mockMvc.perform(post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void TestPostReturn409() throws Exception {
//        String json = """
//            {
//              "id": 1,
//              "name": "Duplicate",
//              "description": "Already exists"
//            }
//        """;
//
//        mockMvc.perform(post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    void TestPutReturn204() throws Exception {
//        String json = """
//            {
//              "id": 1,
//              "name": "Updated Task",
//              "description": "Updated description"
//            }
//        """;
//
//        mockMvc.perform(put("/tasks/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void TestPutReturn404() throws Exception {
//        String json = """
//            {
//              "id": 99,
//              "name": "Missing",
//              "description": "Does not exist"
//            }
//        """;
//
//        mockMvc.perform(put("/tasks/99")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void TestDeleteReturn404() throws Exception {
//        mockMvc.perform(delete("/tasks/99"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void TestGetAfterDelete() throws Exception {
//        mockMvc.perform(delete("/tasks/1"))
//                .andExpect(status().isNoContent());
//
//        mockMvc.perform(get("/tasks/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//    }
//
//}



