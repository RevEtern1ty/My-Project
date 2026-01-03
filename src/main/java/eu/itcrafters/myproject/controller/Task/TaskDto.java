package eu.itcrafters.myproject.controller.Task;

import eu.itcrafters.myproject.persistence.Task.Task;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Task}
 */
@Value
public class TaskDto implements Serializable {
    Long id;
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
    String status;

    @NotNull
    @Size(max = 10)
    String priority;

    LocalDate dueDate;

    Long employeeId;

    String employeeCode;
    String employeeName;

    Long workstationId;

    String workstationCode;
    String workstationName;
}