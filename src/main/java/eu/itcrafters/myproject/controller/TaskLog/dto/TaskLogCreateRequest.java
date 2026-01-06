package eu.itcrafters.myproject.controller.TaskLog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskLogCreateRequest {

    @NotNull
    private Long taskId;

    @NotNull
    @Size(max = 30)
    private String eventType;

    @Size(max = 20)
    private String oldStatus;

    @Size(max = 20)
    private String newStatus;

    @Size(max = 2000)
    private String message;

    private Long createdByEmployeeId;
}