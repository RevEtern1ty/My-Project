package eu.itcrafters.myproject.controller.Employee;


import eu.itcrafters.myproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping ("/employees/all")
    public void getAllEmployees() {
        employeeService.getAllEmployees();

    }



}
