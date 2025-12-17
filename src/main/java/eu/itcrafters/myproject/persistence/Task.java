package eu.itcrafters.myproject.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "TASK_CODE", nullable = false, length = 30)
    private String taskCode;

    @Size(max = 200)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Size(max = 2000)
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Size(max = 20)
    @NotNull
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Size(max = 10)
    @NotNull
    @Column(name = "PRIORITY", nullable = false, length = 10)
    private String priority;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKSTATION_ID")
    private Workstation workstation;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updatedAt;

}