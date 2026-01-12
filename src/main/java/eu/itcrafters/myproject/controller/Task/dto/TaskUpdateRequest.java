package eu.itcrafters.myproject.controller.Task.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TaskUpdateRequest implements Serializable {

    @Size(max = 200)
    String title;

    @Size(max = 200)
    String description;

    @Size(max = 20)
    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    String status;

    @Size(max = 10)
    @Pattern(regexp = "^(LOW|MEDIUM|HIGH)$")
    String priority;

    LocalDate dueDate;

    Long employeeId;
    Long workstationId;
}