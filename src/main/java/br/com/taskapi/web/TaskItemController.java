package br.com.taskapi.web;

import br.com.taskapi.domain.taskItem.TaskItem;
import br.com.taskapi.domain.taskList.TaskList;
import br.com.taskapi.service.taskItem.TaskItemService;
import br.com.taskapi.web.dtos.request.AddTaskItem;
import br.com.taskapi.web.dtos.response.DeleteTaskItem;
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

@RestController
@RequestMapping(value = UriMapper.TASK_ITEM)
@AllArgsConstructor
public class TaskItemController {

    private final TaskItemService taskItemService;

    @PostMapping("/{task_id}/add-item")
    public ResponseEntity<Response<TaskItem>> create(@PathVariable("task_id") UUID id,
                                                     @Valid @RequestBody AddTaskItem item,
                                                     BindingResult result) {
        Response<TaskItem> response = new Response<>();
        try {
            response.setData(taskItemService.create(id, item));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            if (e.getMessage().equals("Lista de Tarefas não encontrada!")) {
                errors.add(e.getMessage());
            }
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/finished/{id}")
    public ResponseEntity<Response<TaskItem>> finished(@PathVariable("id") UUID id) {
        Response<TaskItem> response = new Response<>();
        try {
            response.setData(taskItemService.finished(id));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            if (e.getMessage().equals("Item não encontrado!")) {
                errors.add(e.getMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<DeleteTaskItem>> destroy(@PathVariable("id") UUID id) {
        Response<DeleteTaskItem> response = new Response<>();
        try {
            response.setData(taskItemService.remove(id));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            if (e.getMessage().equals("Item não encontrado!")) {
                errors.add(e.getMessage());
            }
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
