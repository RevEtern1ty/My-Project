package eu.itcrafters.myproject.persistence.Workstation;

import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkstationMapper {





    WorkstationDto toDto(Workstation workstation);
}

