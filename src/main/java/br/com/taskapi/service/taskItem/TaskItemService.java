package br.com.taskapi.service.taskItem;

import br.com.taskapi.domain.taskItem.TaskItem;
import br.com.taskapi.web.dtos.request.AddTaskItem;
import br.com.taskapi.web.dtos.response.DeleteTaskItem;

import java.util.List;
import java.util.UUID;

public interface TaskItemService {
    TaskItem create(UUID id, AddTaskItem item);
    TaskItem finished(UUID id);
    DeleteTaskItem remove(UUID id);
}
