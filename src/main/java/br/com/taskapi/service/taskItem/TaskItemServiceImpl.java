package br.com.taskapi.service.taskItem;

import br.com.taskapi.domain.taskItem.TaskItem;
import br.com.taskapi.domain.taskItem.TaskItemRepository;
import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.domain.taskList.TaskListRepository;
import br.com.taskapi.web.dtos.request.AddTaskItem;
import br.com.taskapi.web.dtos.response.DeleteTaskItem;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskItemServiceImpl implements TaskItemService {

    private final TaskItemRepository taskItemRepository;
    private final TaskListRepository taskListRepository;

    @SneakyThrows
    @Override
    public TaskItem create(UUID id, AddTaskItem item) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isPresent()) {
            TaskItem taskItem = new TaskItem();
            taskItem.setName(item.getName());
            taskItem.setTaskList(taskList.get());
            taskItem.setFinished(false);

            return taskItemRepository.save(taskItem);
        }

        throw new Exception("Lista de Tarefas não encontrada!");


    }

    @SneakyThrows
    @Override
    public TaskItem finished(UUID id) {
        Optional<TaskItem> taskItem = taskItemRepository.findById(id);
        if (taskItem.isPresent()) {
            taskItem.get().setFinished(true);
            return taskItemRepository.save(taskItem.get());
        }

        throw new Exception("Item não encontrado!");
    }

    @SneakyThrows
    @Override
    public DeleteTaskItem remove(UUID id) {
        Optional<TaskItem> taskItem = taskItemRepository.findById(id);
        if (taskItem.isPresent()) {
            taskItemRepository.deleteById(id);
            return new DeleteTaskItem("Item removido com sicesso");
        }

        throw new Exception("Item não encontrado!");
    }
}
