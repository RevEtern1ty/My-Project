package eu.itcrafters.myproject.controller.Employee;


import eu.itcrafters.myproject.controller.Employee.dto.EmployeeCreateRequest;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeDto;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeUpdateRequest;
import eu.itcrafters.myproject.service.Employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping ("/employees/all")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public EmployeeDto createEmployee (@Valid @RequestBody EmployeeCreateRequest request){
        return employeeService.createEmployee(request);
    }

    @PatchMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request){
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }



}
