package eu.itcrafters.myproject.controller.TaskLog.dto;

import eu.itcrafters.myproject.persistence.TaskLog.TaskLog;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link TaskLog}
 */
@Value
public class TaskLogDto implements Serializable {
    Long id;
    Long taskId;
    Instant taskCreatedAt;
    Instant taskUpdatedAt;
    @NotNull
    @Size(max = 30)

    String eventType;
    @Size(max = 20)

    String oldStatus;
    @Size(max = 20)

    String newStatus;
    @Size(max = 20)

    String message;
    Long createdByEmployeeId;
    String createdByEmployeeCode;
    String createdByEmployeeFullName;
    Instant createdByEmployeeCreatedAt;
}