package eu.itcrafters.myproject;

import eu.itcrafters.myproject.controller.Employee.dto.EmployeeCreateRequest;
import eu.itcrafters.myproject.controller.Employee.dto.EmployeeDto;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeMapper;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import eu.itcrafters.myproject.service.Employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldMapEntitiesToDtos() {

        Employee e1 = new Employee();
        Employee e2 = new Employee();
        when(employeeRepository.findAll()).thenReturn(List.of(e1, e2));

        EmployeeDto d1 = new EmployeeDto();
        EmployeeDto d2 = new EmployeeDto();
        when(employeeMapper.toDto(e1)).thenReturn(d1);
        when(employeeMapper.toDto(e2)).thenReturn(d2);


        List<EmployeeDto> result = employeeService.getAllEmployees();


        assertEquals(2, result.size());
        assertSame(d1, result.get(0));
        assertSame(d2, result.get(1));
        verify(employeeRepository).findAll();
        verify(employeeMapper).toDto(e1);
        verify(employeeMapper).toDto(e2);
        verifyNoMoreInteractions(employeeRepository, employeeMapper);
    }

    @Test
    void shouldThrowWhenNotFound() {
        // Arrange
        long id = 999L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(DataNotFoundException.class, () -> employeeService.getEmployee(id));
        verify(employeeRepository).findById(id);
        verifyNoMoreInteractions(employeeRepository, employeeMapper);
    }

    @Test
    void shouldSaveNewEmployeeWhenCodeNotExists() {

        EmployeeCreateRequest request = new EmployeeCreateRequest();
        request.setCode("E-100");
        request.setName("John Doe");

        when(employeeRepository.existsByEmployeeCode("E-100")).thenReturn(false);

        Employee entity = new Employee();
        when(employeeMapper.toEntity(request)).thenReturn(entity);

        Employee saved = new Employee();
        when(employeeRepository.save(entity)).thenReturn(saved);

        EmployeeDto dto = new EmployeeDto();
        when(employeeMapper.toDto(saved)).thenReturn(dto);


        EmployeeDto result = employeeService.createEmployee(request);


        assertSame(dto, result);
        verify(employeeRepository).existsByEmployeeCode("E-100");
        verify(employeeMapper).toEntity(request);
        verify(employeeRepository).save(entity);
        verify(employeeMapper).toDto(saved);
        verifyNoMoreInteractions(employeeRepository, employeeMapper);
    }
}
