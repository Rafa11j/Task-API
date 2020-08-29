package br.com.taskapi.service.taskList;

import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.domain.taskList.TaskListRepository;
import br.com.taskapi.web.dtos.request.CreateTaskListDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList findById(UUID id) {

        if (!taskListRepository.findById(id).isPresent()) {
            return null;
        }
        return taskListRepository.findById(id).get();
    }

    @SneakyThrows
    @Override
    public TaskList create(CreateTaskListDTO createTaskList) {
        Optional<TaskList> verifyTaskName = taskListRepository.findByName(createTaskList.getName());
        if (verifyTaskName.isPresent()) {
            throw new Exception("Já existe uma lista de tarefas com este nome!");
        }

        TaskList taskList = new TaskList();
        taskList.setName(createTaskList.getName());

        return taskListRepository.save(taskList);
    }
}
