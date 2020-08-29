package br.com.taskapi.web.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AddTaskItem {

    @NotNull(message = "O nome da tarefa não pode ser nulo!")
    private String name;
}
