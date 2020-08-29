package br.com.taskapi.web.dtos.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateTaskListDTO {

    @NotNull(message = "O nome da lista não pode ser nulo!")
    private String name;
}
