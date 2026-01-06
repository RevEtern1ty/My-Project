package eu.itcrafters.myproject.service.TaskLog;

import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogCreateRequest;
import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogDto;
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
        List<TaskLog> logs =taskLogRepository.findByTaskId(taskId);

        List<TaskLogDto> result = new ArrayList<>();

        for (TaskLog log : logs){
            result.add(taskLogMapper.toDto(log));
        }
        return result;
    }

    @Transactional
    public TaskLogDto createLog (TaskLogCreateRequest request){
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found "+request.getTaskId()));

        TaskLog entity = taskLogMapper.toEntity(request);
        entity.setTask(task);
        entity.setCreatedAt(Instant.now());

        if (request.getCreatedByEmployeeId() != null){
            Employee employee = employeeRepository.findById(request.getCreatedByEmployeeId())
                    .orElseThrow(() ->new IllegalArgumentException("Employee not found "+request.getCreatedByEmployeeId()));

            entity.setCreatedByEmployee(employee);
        }

        TaskLog saved = taskLogRepository.save(entity);
        return taskLogMapper.toDto(saved);

    }


}

