package eu.itcrafters.myproject.controller.Employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class EmployeeCreateRequest {
    @NotBlank
    @Size(max = 20)
    String code;

    @NotBlank
    @Size(max = 120)
    String name;

    @Size(max = 120)
    String email;
}

