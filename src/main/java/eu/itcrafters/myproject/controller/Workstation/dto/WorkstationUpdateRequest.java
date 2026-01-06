package eu.itcrafters.myproject.controller.Workstation.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkstationUpdateRequest implements Serializable {

    @Size(max = 80)
    private String name;

    @Size(max = 80)
    private String area;

    private Boolean isActive;
}
