package eu.itcrafters.myproject.persistence.Task;

import eu.itcrafters.myproject.controller.Task.TaskDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "workstation.id", target = "workstationId")

    TaskDto toDto(Task task);

    List<TaskDto> toDtoList(List<Task> tasks);

}