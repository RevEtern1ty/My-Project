package eu.itcrafters.myproject.service.Employee;

import eu.itcrafters.myproject.controller.Employee.dto.EmployeeCreateRequest;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeDto;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeUpdateRequest;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeMapper;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

   @Transactional
    public List<EmployeeDto> getAllEmployees() {
        // Convert entity list to DTO list
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto).toList();
    }

    @Transactional
    public EmployeeDto getEmployee (Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Empl not found " +id));

        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeCreateRequest request){
       // Validate existance
        if (employeeRepository.existsByEmployeeCode(request.getCode())){
            throw new IllegalArgumentException("Employee with same id already exists "+ request.getCode());
        }
        if (request.getEmail() != null && employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Employee email already exists: " + request.getEmail());
        }
        Employee employee = employeeMapper.toEntity(request);
        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        // Optional: validate email uniqueness if email is updated
        if (request.getEmail() != null && employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Employee email already exists: " + request.getEmail());
        }

        employeeMapper.updateEntity(request, employee);
        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        employeeRepository.delete(employee);
    }
}

