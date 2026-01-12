package eu.itcrafters.myproject.service.Task;

import eu.itcrafters.myproject.controller.Task.dto.TaskCreateRequest;
import eu.itcrafters.myproject.controller.Task.dto.TaskDto;
import eu.itcrafters.myproject.controller.Task.dto.TaskUpdateRequest;
import eu.itcrafters.myproject.infrastructure.rest.error.ErrorCode;
import eu.itcrafters.myproject.infrastructure.rest.exception.BadRequestException;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Employee.Employee;
import eu.itcrafters.myproject.persistence.Employee.EmployeeRepository;
import eu.itcrafters.myproject.persistence.Task.Task;
import eu.itcrafters.myproject.persistence.Task.TaskMapper;
import eu.itcrafters.myproject.persistence.Task.TaskRepository;
import eu.itcrafters.myproject.persistence.Workstation.Workstation;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkstationRepository workstationRepository;
    private final TaskMapper taskMapper;


    @Transactional
    public List<TaskDto> getAllTasks(){
        // Fetch entities
        List<Task> tasks = taskRepository.findAll();

        // Convert entity list -> DTO list
        List<TaskDto> result = new ArrayList<>();
        for (Task task : tasks) {
            result.add(taskMapper.toDto(task));
        }
        return result;
    }

    @Transactional
    public TaskDto getTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                ErrorCode.TASK_NOT_FOUND.format("id", id)));
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskDto createTask(TaskCreateRequest request){

        // Map request -> entity
        Task task = taskMapper.toEntity(request);

        // Resolve and set relations
        task.setEmployee(resolveEmployee(request.getEmployeeId()));
        task.setWorkstation(resolveWorkstation(request.getWorkstationId()));

        //Set timestamps
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    @Transactional
    public TaskDto updateTask (Long id, TaskUpdateRequest request){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.TASK_NOT_FOUND.format("id", id)));

        // Patch simple fields
        taskMapper.updateEntity(request,task);

        // Patch relations if IDs provided
        if (request.getEmployeeId() != null){
            task.setEmployee(resolveEmployee(request.getEmployeeId()));
        }
        if (request.getWorkstationId() != null){
            task.setWorkstation(resolveWorkstation(request.getWorkstationId()));
        }

        task.setUpdatedAt(Instant.now());

        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    @Transactional
    public void deleteTask (Long id){
        // Ensure task exists before delete
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.TASK_NOT_FOUND.format("id", id)));

        taskRepository.delete(task);
    }

   // Helpers
    private Employee resolveEmployee(Long employeeId) {
        if (employeeId == null) {
            throw new BadRequestException("employeeId is required");
        }
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.EMPLOYEE_NOT_FOUND.format("id", employeeId)));
    }
    private Workstation resolveWorkstation(Long workstationId) {
        if (workstationId == null) {
            throw new BadRequestException("workstationId is required");
        }
        return workstationRepository.findById(workstationId)
                .orElseThrow(() -> new DataNotFoundException(
                        ErrorCode.WORKSTATION_NOT_FOUND.format("id", workstationId)));
    }


}
