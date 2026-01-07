package eu.itcrafters.myproject.controller.Task.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TaskCreateRequest implements Serializable {

    @NotNull
    @Size(max = 30)
    String taskCode;

    @NotNull
    @Size(max = 200)
    String title;

    @Size(max = 200)
    String description;

    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    String status;

    @NotNull
    @Size(max = 10)
    @Pattern(regexp = "^(LOW|MEDIUM|HIGH)$")
    String priority;

    LocalDate dueDate;

    Long employeeId;
    Long workstationId;
}