package eu.itcrafters.myproject.persistence.TaskLog;

import eu.itcrafters.myproject.controller.TaskLog.TaskLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskLogMapper {
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "createdByEmployee.id", target = "createdByEmployeeId")
    @Mapping(source = "createdByEmployee.employeeCode", target = "createdByEmployeeCode")
    @Mapping(source = "createdByEmployee.fullName", target = "createdByEmployeeFullName")
    TaskLogDto toDto(TaskLog taskLog);


}