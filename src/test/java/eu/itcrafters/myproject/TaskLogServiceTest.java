package eu.itcrafters.myproject;

import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogCreateRequest;
import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogDto;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import eu.itcrafters.myproject.persistence.Task.Task;
import eu.itcrafters.myproject.persistence.Task.TaskRepository;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLog;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLogMapper;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLogRepository;
import eu.itcrafters.myproject.service.TaskLog.TaskLogService;
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
public class TaskLogServiceTest {

    @Mock private TaskRepository taskRepository;
    @Mock private TaskLogRepository taskLogRepository;
    @Mock private TaskLogMapper taskLogMapper;
    @Mock private EmployeeRepository employeeRepository;

    @InjectMocks
    private TaskLogService taskLogService;

    @Test
    void shouldThrowWhenTaskDoesNotExist() {
        long taskId = 999L;

        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> taskLogService.getLogsByTaskId(taskId));

        verify(taskRepository).existsById(taskId);
        verifyNoInteractions(taskLogRepository, taskLogMapper, employeeRepository);
    }

    @Test
    void shouldReturnDtosWhenTaskExists() {
        long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        TaskLog log1 = new TaskLog();
        TaskLog log2 = new TaskLog();

        when(taskLogRepository.findByTaskId(taskId)).thenReturn(List.of(log1, log2));

        TaskLogDto dto1 = new TaskLogDto();
        TaskLogDto dto2 = new TaskLogDto();

        when(taskLogMapper.toDto(log1)).thenReturn(dto1);
        when(taskLogMapper.toDto(log2)).thenReturn(dto2);

        List<TaskLogDto> result = taskLogService.getLogsByTaskId(taskId);

        assertEquals(2, result.size());
        assertSame(dto1, result.get(0));
        assertSame(dto2, result.get(1));

        verify(taskRepository).existsById(taskId);
        verify(taskLogRepository).findByTaskId(taskId);
        verify(taskLogMapper).toDto(log1);
        verify(taskLogMapper).toDto(log2);
        verifyNoMoreInteractions(taskRepository, taskLogRepository, taskLogMapper, employeeRepository);
    }

    @Test
    void shouldThrowWhenTaskNotFound() {
        TaskLogCreateRequest request = new TaskLogCreateRequest();
        request.setTaskId(404L);

        when(taskRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> taskLogService.createLog(request));

        verify(taskRepository).findById(404L);
        verifyNoMoreInteractions(taskRepository);
        verifyNoInteractions(taskLogRepository, taskLogMapper, employeeRepository);
    }

    @Test
    void shouldCreateWithoutCreatedByEmployee() {
        TaskLogCreateRequest request = new TaskLogCreateRequest();
        request.setTaskId(10L);
        request.setCreatedByEmployeeId(null);

        Task task = new Task();
        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        TaskLog entity = new TaskLog();
        when(taskLogMapper.toEntity(request)).thenReturn(entity);

        TaskLog saved = new TaskLog();
        when(taskLogRepository.save(entity)).thenReturn(saved);

        TaskLogDto dto = new TaskLogDto();
        when(taskLogMapper.toDto(saved)).thenReturn(dto);

        TaskLogDto result = taskLogService.createLog(request);

        assertSame(dto, result);

        // relations set in service
        assertSame(task, entity.getTask());
        assertNotNull(entity.getCreatedAt());

        // createdByEmployee should remain null
        assertNull(entity.getCreatedByEmployee());

        verify(taskRepository).findById(10L);
        verify(taskLogMapper).toEntity(request);
        verify(taskLogRepository).save(entity);
        verify(taskLogMapper).toDto(saved);
        verifyNoMoreInteractions(taskRepository, taskLogRepository, taskLogMapper);
        verifyNoInteractions(employeeRepository);
    }

    @Test
    void shouldSetCreatedByEmployee() {
        TaskLogCreateRequest request = new TaskLogCreateRequest();
        request.setTaskId(10L);
        request.setCreatedByEmployeeId(77L);

        Task task = new Task();
        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        TaskLog entity = new TaskLog();
        when(taskLogMapper.toEntity(request)).thenReturn(entity);

        Employee employee = new Employee();
        when(employeeRepository.findById(77L)).thenReturn(Optional.of(employee));

        TaskLog saved = new TaskLog();
        when(taskLogRepository.save(entity)).thenReturn(saved);

        TaskLogDto dto = new TaskLogDto();
        when(taskLogMapper.toDto(saved)).thenReturn(dto);

        TaskLogDto result = taskLogService.createLog(request);

        assertSame(dto, result);

        assertSame(task, entity.getTask());
        assertNotNull(entity.getCreatedAt());
        assertSame(employee, entity.getCreatedByEmployee());

        verify(taskRepository).findById(10L);
        verify(taskLogMapper).toEntity(request);
        verify(employeeRepository).findById(77L);
        verify(taskLogRepository).save(entity);
        verify(taskLogMapper).toDto(saved);
        verifyNoMoreInteractions(taskRepository, taskLogRepository, taskLogMapper, employeeRepository);
    }

    @Test
    void shouldThrowWhenCreatedByEmployeeNotFound() {
        TaskLogCreateRequest request = new TaskLogCreateRequest();
        request.setTaskId(10L);
        request.setCreatedByEmployeeId(77L);

        Task task = new Task();
        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        TaskLog entity = new TaskLog();
        when(taskLogMapper.toEntity(request)).thenReturn(entity);

        when(employeeRepository.findById(77L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> taskLogService.createLog(request));

        verify(taskRepository).findById(10L);
        verify(taskLogMapper).toEntity(request);
        verify(employeeRepository).findById(77L);

        // save must not happen
        verifyNoInteractions(taskLogRepository);
    }
}

