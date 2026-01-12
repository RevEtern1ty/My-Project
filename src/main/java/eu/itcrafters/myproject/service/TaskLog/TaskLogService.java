package eu.itcrafters.myproject.service.TaskLog;

import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogCreateRequest;
import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogDto;
import eu.itcrafters.myproject.infrastructure.rest.error.ErrorCode;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import eu.itcrafters.myproject.persistence.Task.Task;
import eu.itcrafters.myproject.persistence.Task.TaskRepository;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLog;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLogMapper;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskLogService {

    private final TaskLogRepository taskLogRepository;
    private final TaskRepository taskRepository;
    private final TaskLogMapper taskLogMapper;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public List<TaskLogDto> getLogsByTaskId(Long taskId) {
        // Checking existance
        ensureTaskExists(taskId);

        // Fetch all TaskLog entities related to the given Task ID
        List<TaskLog> logs =taskLogRepository.findByTaskId(taskId);

        // Prepare result list for DTOs
        List<TaskLogDto> result = new ArrayList<>();

        // Convert each TaskLog entity to DTO manually
        for (TaskLog log : logs){
            result.add(taskLogMapper.toDto(log));
        }
        return result;
    }

    @Transactional
    public TaskLogDto createLog (TaskLogCreateRequest request){

        // Load Task entity and fail if it does not exist
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.TASK_NOT_FOUND.format("id", request.getTaskId())));

        TaskLog entity = taskLogMapper.toEntity(request);
        entity.setTask(task);
        entity.setCreatedAt(Instant.now());

        // If creator employee ID is provided, resolve and assign relation
        if (request.getCreatedByEmployeeId() != null){
            Employee employee = employeeRepository.findById(request.getCreatedByEmployeeId())
                    .orElseThrow(() -> new DataNotFoundException(
                            ErrorCode.EMPLOYEE_NOT_FOUND.format("id", request.getCreatedByEmployeeId())));

            entity.setCreatedByEmployee(employee);
        }

        TaskLog saved = taskLogRepository.save(entity);
        return taskLogMapper.toDto(saved);

    }

    // Helpers
    private void ensureTaskExists(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new DataNotFoundException(ErrorCode.TASK_NOT_FOUND.format("id", taskId));
        }
    }



}

