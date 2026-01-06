package eu.itcrafters.myproject;


import eu.itcrafters.myproject.controller.Task.dto.TaskCreateRequest;
import eu.itcrafters.myproject.controller.Task.dto.TaskDto;
import eu.itcrafters.myproject.controller.Task.dto.TaskUpdateRequest;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import eu.itcrafters.myproject.persistence.Task.Task;
import eu.itcrafters.myproject.persistence.Task.TaskMapper;
import eu.itcrafters.myproject.persistence.Task.TaskRepository;
import eu.itcrafters.myproject.persistence.Workstation.Workstation;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationRepository;
import eu.itcrafters.myproject.service.Task.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock private TaskRepository taskRepository;
    @Mock private TaskMapper taskMapper;
    @Mock private EmployeeRepository employeeRepository;
    @Mock private WorkstationRepository workstationRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldThrowWhenNotFound() {
        long id = 404L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> taskService.getTask(id));
        verify(taskRepository).findById(id);
        verifyNoMoreInteractions(taskRepository, taskMapper, employeeRepository, workstationRepository);
    }

    @Test
    void shouldSetRelationsAndSave() {
        // Arrange
        TaskCreateRequest request = new TaskCreateRequest();
        request.setEmployeeId(10L);
        request.setWorkstationId(20L);
        request.setTaskCode("T-1");
        request.setTitle("Test");

        Employee employee = new Employee();
        Workstation workstation = new Workstation();

        when(employeeRepository.findById(10L)).thenReturn(Optional.of(employee));
        when(workstationRepository.findById(20L)).thenReturn(Optional.of(workstation));

        Task entity = new Task();
        when(taskMapper.toEntity(request)).thenReturn(entity);

        Task saved = new Task();
        when(taskRepository.save(entity)).thenReturn(saved);

        TaskDto dto = new TaskDto();
        when(taskMapper.toDto(saved)).thenReturn(dto);

        // Act
        TaskDto result = taskService.createTask(request);


        assertSame(dto, result);
        assertSame(employee, entity.getEmployee());
        assertSame(workstation, entity.getWorkstation());

        verify(employeeRepository).findById(10L);
        verify(workstationRepository).findById(20L);
        verify(taskMapper).toEntity(request);
        verify(taskRepository).save(entity);
        verify(taskMapper).toDto(saved);
        verifyNoMoreInteractions(taskRepository, taskMapper, employeeRepository, workstationRepository);
    }

    @Test
    void shouldPatchFieldsAndUpdateRelationsIfProvided() {
        // Arrange
        long id = 1L;
        TaskUpdateRequest request = new TaskUpdateRequest();
        request.setEmployeeId(11L);
        request.setWorkstationId(21L);

        Task task = new Task();
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        Employee newEmployee = new Employee();
        Workstation newWorkstation = new Workstation();

        when(employeeRepository.findById(11L)).thenReturn(Optional.of(newEmployee));
        when(workstationRepository.findById(21L)).thenReturn(Optional.of(newWorkstation));

        doNothing().when(taskMapper).updateEntity(request, task);

        Task saved = new Task();
        when(taskRepository.save(task)).thenReturn(saved);

        TaskDto dto = new TaskDto();
        when(taskMapper.toDto(saved)).thenReturn(dto);


        TaskDto result = taskService.updateTask(id, request);

        assertSame(dto, result);
        verify(taskRepository).findById(id);
        verify(taskMapper).updateEntity(request, task);
        verify(employeeRepository).findById(11L);
        verify(workstationRepository).findById(21L);
        verify(taskRepository).save(task);
        verify(taskMapper).toDto(saved);

        assertSame(newEmployee, task.getEmployee());
        assertSame(newWorkstation, task.getWorkstation());
    }
}
