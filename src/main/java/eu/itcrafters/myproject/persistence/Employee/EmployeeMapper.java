package eu.itcrafters.myproject.persistence.Employee;

import eu.itcrafters.myproject.controller.Employee.dto.EmployeeCreateRequest;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeDto;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeUpdateRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    // Map entity -> response DTO
    @Mapping(source = "id",target = "employeeId")
    @Mapping(source = "employeeCode",target = "code")
    @Mapping(source = "fullName",target = "name")
    EmployeeDto toDto(Employee employee);

    // Map create request -> entity
    @Mapping(source = "code", target = "employeeCode")
    @Mapping(source = "name", target = "fullName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Employee toEntity(EmployeeCreateRequest request);

    // Patch update: update only non-null fields
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "fullName")
    void updateEntity(EmployeeUpdateRequest request, @MappingTarget Employee employee);

}