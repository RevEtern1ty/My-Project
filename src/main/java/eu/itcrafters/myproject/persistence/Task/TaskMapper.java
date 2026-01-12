package eu.itcrafters.myproject.persistence.Task;

import eu.itcrafters.myproject.controller.Task.dto.TaskCreateRequest;
import eu.itcrafters.myproject.controller.Task.dto.TaskDto;
import eu.itcrafters.myproject.controller.Task.dto.TaskUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    // Map entity -> DTO (flatten relations)
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.employeeCode", target = "employeeCode")
    @Mapping(source = "employee.fullName", target = "employeeName")
    @Mapping(source = "workstation.id", target = "workstationId")
    @Mapping(source = "workstation.code", target = "workstationCode")
    @Mapping(source = "workstation.name", target = "workstationName")
    TaskDto toDto(Task task);

    // Map list entity -> list DTO
    List<TaskDto> toDtoList(List<Task> tasks);

    // Map create request -> entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "workstation", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskCreateRequest request);

    // Patch update: update only non-null fields
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "taskCode", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "workstation", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(TaskUpdateRequest request, @MappingTarget Task task);
}