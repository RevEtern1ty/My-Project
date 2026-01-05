package eu.itcrafters.myproject.controller.Task.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class TaskUpdateRequest implements Serializable {

    @Size(max = 200)
    String title;

    @Size(max = 200)
    String description;

    @Size(max = 20)
    String status;

    @Size(max = 10)
    String priority;

    LocalDate dueDate;

    Long employeeId;
    Long workstationId;
}