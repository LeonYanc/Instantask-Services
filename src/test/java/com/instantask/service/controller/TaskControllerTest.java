package com.instantask.service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instantask.service.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateReadUpdateDelete() throws Exception {
        Task newTask = new Task();
        newTask.setName("testIntegrationTask_" + new Random().nextInt(9999));
        newTask.setAssignee("testAssignee");
        newTask.setReporter("testReporter");
        newTask.setStatus("testStatus");
        newTask.setIssuedTime(new Date());
        newTask.setDueTime(new Date());
        newTask.setVisibility(true);
        newTask.setAttachedTask(Collections.emptyList());
        newTask.setComments(Collections.emptyList());
        newTask.setHistory(Collections.emptyList());
        newTask.setDescription("This is a test integration task");

        // =========== 1. POST ===========
        MvcResult createResult = mockMvc.perform(post("/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(newTask.getName()))
                .andReturn();

        String createResponseStr = createResult.getResponse().getContentAsString();
        System.out.println(">>> POST Response: " + createResponseStr);

        JsonNode createJson = objectMapper.readTree(createResponseStr);
        String createdTaskId = createJson.get("id").asText();

        // =========== 2. GET ===========
        MvcResult getResult = mockMvc.perform(get("/tasks/" + createdTaskId))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseStr = getResult.getResponse().getContentAsString();
        System.out.println(">>> GET Response: " + getResponseStr);

        // =========== 3. PUT ===========
        newTask.setName("updatedName");
        newTask.setDescription("updatedDescription");

        MvcResult updateResult = mockMvc.perform(put("/tasks/" + createdTaskId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("updatedName"))
                .andExpect(jsonPath("$.description").value("updatedDescription"))
                .andReturn();

        String updateResponseStr = updateResult.getResponse().getContentAsString();
        System.out.println(">>> PUT Response: " + updateResponseStr);

        JsonNode updateJson = objectMapper.readTree(updateResponseStr);
        assertThat(updateJson.get("name").asText()).isEqualTo("updatedName");

        // =========== 4. DELETE ===========
        MvcResult deleteResult = mockMvc.perform(delete("/tasks/" + createdTaskId))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(">>> DELETE Status: " + deleteResult.getResponse().getStatus());

        // =========== 5. READ after DELETE (GET) ===========
        MvcResult afterDeleteResult = mockMvc.perform(get("/tasks/" + createdTaskId))
                .andExpect(status().isOk())
                .andReturn();

        String afterDeleteBody = afterDeleteResult.getResponse().getContentAsString();
        System.out.println(">>> GET (After DELETE) Response: " + afterDeleteBody);

        assertThat(afterDeleteBody).isEmpty();
    }
}
