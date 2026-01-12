package eu.itcrafters.myproject.controller.Workstation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.io.Serializable;


@Data
public class WorkstationDto implements Serializable {
    Long id;

    @NotNull
    @Size(max = 20)
    String code;

    @NotNull
    @Size(max = 80)
    String name;

    @Size(max = 80)
    String area;

    Boolean isActive;
}