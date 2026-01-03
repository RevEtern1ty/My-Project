package eu.itcrafters.myproject.service.employee;

import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void getAllEmployees() {
        employeeRepository.findAll();
    }
}
