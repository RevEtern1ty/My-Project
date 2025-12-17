package eu.itcrafters.myproject.persistence.Employee;

import eu.itcrafters.myproject.controller.EmployeeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    @Mapping(source = "id",target = "employeeId")
    @Mapping(source = "employeeCode",target = "code")
    @Mapping(source = "fullName",target = "name")
    EmployeeDto toDto(Employee employee);
}