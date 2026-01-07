package eu.itcrafters.myproject.controller.TaskLog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskLogCreateRequest {

    @NotNull
    private Long taskId;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^(CREATED|STATUS_CHANGE|COMMENT|ASSIGNMENT_CHANGE)$")
    private String eventType;

    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    @Size(max = 20)
    private String oldStatus;

    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    @Size(max = 20)
    private String newStatus;

    @Size(max = 2000)
    private String message;

    private Long createdByEmployeeId;
}