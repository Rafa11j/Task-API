package br.com.taskapi;

import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.service.taskList.TaskListService;
import br.com.taskapi.web.dtos.request.CreateTaskListDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskListTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskListService taskListService;

    @Test
    public void createTaskList() {
        try {
            CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
            createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

            mockMvc.perform(post("/api/v1/tasks")
                .contentType("application/json")
                    .content(objectMapper.writeValueAsString(createTaskListDTO))).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loadAllTaskLists() {
        try {

            CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
            createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

            mockMvc.perform(get("/api/v1/tasks")).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findTaskLists() {
        CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
        createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

        TaskList taskList = taskListService.create(createTaskListDTO);

        Assertions.assertNotNull(taskList);

        try {
            mockMvc.perform(get("/api/v1/tasks/" + taskList.getId())).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
