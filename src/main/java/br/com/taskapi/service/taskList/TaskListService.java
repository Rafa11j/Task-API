package br.com.taskapi.service.taskList;

import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.web.dtos.request.CreateTaskListDTO;

import java.util.List;
import java.util.UUID;

public interface TaskListService {

    List<TaskList> findAll();
    TaskList findById(UUID id);
    TaskList create(CreateTaskListDTO createTaskList);
}
