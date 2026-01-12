package eu.itcrafters.myproject.service.Employee;

import eu.itcrafters.myproject.controller.Employee.dto.EmployeeCreateRequest;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeDto;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeUpdateRequest;
import eu.itcrafters.myproject.infrastructure.rest.error.ErrorCode;
import eu.itcrafters.myproject.infrastructure.rest.exception.BadRequestException;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeMapper;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

   @Transactional
    public List<EmployeeDto> getAllEmployees() {
        // Fetch all employees from DB
       List<Employee> employees = employeeRepository.findAll();

       // Map entities to DTOs (without streams for clarity)
       List<EmployeeDto> result = new ArrayList<>();
       for (Employee employee : employees) {
           result.add(employeeMapper.toDto(employee));
       }

       return result;
    }

    @Transactional
    public EmployeeDto getEmployee (Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException(
                        ErrorCode.EMPLOYEE_NOT_FOUND.format("id", id)));

        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeCreateRequest request){
       // Validate existance
        if (employeeRepository.existsByEmployeeCode(request.getCode())){
           throw new BadRequestException(
                    ErrorCode.EMPLOYEE_CODE_EXISTS.format("code", request.getCode()));
        }
        if (request.getEmail() != null && employeeRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(
                    ErrorCode.EMPLOYEE_EMAIL_EXISTS.format("email", request.getEmail()));
        }

        // Map request -> entity  and save
        Employee employee = employeeMapper.toEntity(request);
        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.EMPLOYEE_NOT_FOUND.format("id", id)));

        // Optional: validate email uniqueness if email is updated
        if (request.getEmail() != null && employeeRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(
                    ErrorCode.EMPLOYEE_EMAIL_EXISTS.format("email", request.getEmail()));
        }

        employeeMapper.updateEntity(request, employee);
        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                ErrorCode.EMPLOYEE_NOT_FOUND.format("id", id)));

        employeeRepository.delete(employee);
    }
}

