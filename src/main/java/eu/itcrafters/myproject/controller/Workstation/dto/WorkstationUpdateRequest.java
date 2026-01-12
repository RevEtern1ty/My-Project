package eu.itcrafters.myproject.controller.Workstation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkstationUpdateRequest implements Serializable {

    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    @Size(max = 80)
    private String area;

    private Boolean isActive;
}
