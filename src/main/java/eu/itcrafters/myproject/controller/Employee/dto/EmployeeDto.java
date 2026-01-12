package eu.itcrafters.myproject.controller.Employee.dto;

import eu.itcrafters.myproject.persistence.Employee.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private Long employeeId;
    @NotNull
    @Size(max = 20)
    private String code;
    @NotNull
    @Size(max = 120)
    private String name;

    @Email
    @Size(max = 120)
    private String email;

    private Boolean isActive;
}