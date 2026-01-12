package eu.itcrafters.myproject.controller.TaskLog.dto;

import eu.itcrafters.myproject.persistence.TaskLog.TaskLog;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TaskLog}
 */
@Data
public class TaskLogDto implements Serializable {
    Long id;
    Long taskId;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^(CREATED|STATUS_CHANGE|COMMENT|ASSIGNMENT_CHANGE)$")
    String eventType;
    @Size(max = 20)

    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    String oldStatus;
    @Size(max = 20)

    @Pattern(regexp = "^(TODO|IN_PROGRESS|DONE|CANCELLED)$")
    String newStatus;

    @Size(max = 2000)
    String message;

    Long createdByEmployeeId;
    String createdByEmployeeCode;
    String createdByEmployeeFullName;
    Instant createdByEmployeeCreatedAt;
}