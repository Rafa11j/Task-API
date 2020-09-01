package br.com.taskapi.web;

import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.domain.taskList.TaskListRepository;
import br.com.taskapi.service.taskList.TaskListService;
import br.com.taskapi.web.dtos.request.CreateTaskListDTO;
import br.com.taskapi.web.utils.Response;
import br.com.taskapi.web.utils.UriMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = UriMapper.TASK_LIST)
@RestController
@AllArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListRepository taskListRepository;

    @GetMapping
    public List<TaskList> index() {
        return taskListService.findAll();
    }

    @GetMapping("/{id}")
    public TaskList show(@PathVariable("id")UUID id) {
        return taskListService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Response<TaskList>> create(@Valid @RequestBody CreateTaskListDTO createTaskListDTO,
                                                     BindingResult result) {
        Response<TaskList> response = new Response<>();
        try {
            response.setData(taskListService.create(createTaskListDTO));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            if (e.getMessage().equals("Já existe uma lista de tarefas com este nome!")) {
                errors.add(e.getMessage());
            }
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
