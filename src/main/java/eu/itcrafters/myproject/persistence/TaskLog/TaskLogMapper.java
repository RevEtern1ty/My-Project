package eu.itcrafters.myproject.persistence.TaskLog;

import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogCreateRequest;
import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskLogMapper {
    // Map entity -> DTO
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "createdByEmployee.id", target = "createdByEmployeeId")
    @Mapping(source = "createdByEmployee.fullName", target = "createdByEmployeeFullName")
    TaskLogDto toDto(TaskLog entity);

    // Map create request -> entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "createdByEmployee", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TaskLog toEntity(TaskLogCreateRequest request);


}