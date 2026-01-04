package eu.itcrafters.myproject.controller.Employee.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class EmployeeUpdateRequest {
    @Size(max = 120)
    String name;

    @Size(max = 120)
    String email;

    Boolean isActive;
}
