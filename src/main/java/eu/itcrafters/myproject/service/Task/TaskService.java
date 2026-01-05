package eu.itcrafters.myproject.service.Task;

import eu.itcrafters.myproject.controller.Task.dto.TaskCreateRequest;
import eu.itcrafters.myproject.controller.Task.dto.TaskDto;
import eu.itcrafters.myproject.controller.Task.dto.TaskUpdateRequest;
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
        // Convert entity list to DTO list
       return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Transactional
    public TaskDto getTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Task not found "+id));
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskDto createTask(TaskCreateRequest request){

        // Map request -> entity
        Task task = taskMapper.toEntity(request);

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
                .orElseThrow(() -> new IllegalArgumentException("Task not found "+id));

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
        if (!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Task not found "+id);
        }
        taskRepository.deleteById(id);
    }

   // Helpers
    private Employee resolveEmployee(Long employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeId));
    }
    private Workstation resolveWorkstation(Long workstationId) {
        if (workstationId == null) {
            return null;
        }
        return workstationRepository.findById(workstationId)
                .orElseThrow(() -> new IllegalArgumentException("Workstation not found: " + workstationId));
    }


}
