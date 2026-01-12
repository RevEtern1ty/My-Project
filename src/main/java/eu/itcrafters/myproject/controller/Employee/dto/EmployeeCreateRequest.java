package eu.itcrafters.myproject.controller.Employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeCreateRequest {
    @NotBlank
    @Size(max = 20)
    String code;

    @NotBlank
    @Size(max = 120)
    String name;

    @Email
    @Size(max = 120)
    String email;
}

