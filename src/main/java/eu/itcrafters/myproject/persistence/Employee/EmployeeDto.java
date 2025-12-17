package eu.itcrafters.myproject.persistence.Employee;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    private Long id;
    @NotNull
    @Size(max = 20)
    private String employeeCode;
    @NotNull
    @Size(max = 120)
    private String fullName;
}