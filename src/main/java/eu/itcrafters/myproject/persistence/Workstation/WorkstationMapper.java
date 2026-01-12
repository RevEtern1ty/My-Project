package eu.itcrafters.myproject.persistence.Workstation;

import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationCreateRequest;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationDto;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationUpdateRequest;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkstationMapper {

    // Map entity -> DTO
    WorkstationDto toDto(Workstation workstation);

    // Map create request -> entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Workstation toEntity(WorkstationCreateRequest request);

    // Patch update: update only non-null fields
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(WorkstationUpdateRequest request, @MappingTarget Workstation workstation);

}

