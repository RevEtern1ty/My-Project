package eu.itcrafters.myproject.persistence.Employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "EMPLOYEE_CODE", nullable = false, length = 20)
    private String employeeCode;

    @Size(max = 120)
    @NotNull
    @Column(name = "FULL_NAME", nullable = false, length = 120)
    private String fullName;

    @Size(max = 120)
    @Column(name = "EMAIL", length = 120)
    private String email;

    @NotNull
    @ColumnDefault("TRUE")
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

}