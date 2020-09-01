package br.com.taskapi;

import br.com.taskapi.domain.taskItem.TaskItem;
import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.service.taskItem.TaskItemService;
import br.com.taskapi.service.taskList.TaskListService;
import br.com.taskapi.web.dtos.request.AddTaskItem;
import br.com.taskapi.web.dtos.request.CreateTaskListDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskItemTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskItemService taskItemService;

    @Autowired
    private TaskListService taskListService;

    @Test
    public void createTaskItem() {
        try {
            CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
            createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

            TaskList taskList = taskListService.create(createTaskListDTO);

            AddTaskItem addTaskItem = new AddTaskItem();
            addTaskItem.setName("Item-" + UUID.randomUUID());

            mockMvc.perform(post("/api/v1/task-item/" + taskList.getId() + "/add-item")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(addTaskItem))).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void finishedTaskItem() {

        try {

            CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
            createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

            TaskList taskList = taskListService.create(createTaskListDTO);

            Assertions.assertNotNull(taskList);

            AddTaskItem addTaskItem = new AddTaskItem();
            addTaskItem.setName("Item-" + UUID.randomUUID());

            TaskItem taskItem = taskItemService.create(taskList.getId(), addTaskItem);
            Assertions.assertNotNull(taskItem);

            mockMvc.perform(patch("/api/v1/task-item/finished/" + taskItem.getId())).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteTaskItem() {

        try {

            CreateTaskListDTO createTaskListDTO = new CreateTaskListDTO();
            createTaskListDTO.setName("Tarefa-" + UUID.randomUUID());

            TaskList taskList = taskListService.create(createTaskListDTO);

            Assertions.assertNotNull(taskList);

            AddTaskItem addTaskItem = new AddTaskItem();
            addTaskItem.setName("Item-" + UUID.randomUUID());

            TaskItem taskItem = taskItemService.create(taskList.getId(), addTaskItem);
            Assertions.assertNotNull(taskItem);

            mockMvc.perform(delete("/api/v1/task-item/" + taskItem.getId())).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
