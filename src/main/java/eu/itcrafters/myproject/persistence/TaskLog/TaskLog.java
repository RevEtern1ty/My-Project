package eu.itcrafters.myproject.persistence.TaskLog;

import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "TASK_LOG")
public class TaskLog {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;

    @Size(max = 30)
    @NotNull
    @Column(name = "EVENT_TYPE", nullable = false, length = 30)
    private String eventType;

    @Size(max = 20)
    @Column(name = "OLD_STATUS", length = 20)
    private String oldStatus;

    @Size(max = 20)
    @Column(name = "NEW_STATUS", length = 20)
    private String newStatus;

    @Size(max = 2000)
    @Column(name = "MESSAGE", length = 2000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY_EMPLOYEE_ID")
    private Employee createdByEmployee;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

}