package eu.itcrafters.myproject.controller.Workstation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkstationCreateRequest  implements Serializable {

    @NotNull
    @Size(max = 20)
    private String code;

    @NotNull
    @Size(max = 80)
    private String name;

    @Size(max = 80)
    private String area;
}
