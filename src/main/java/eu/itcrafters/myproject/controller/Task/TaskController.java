package eu.itcrafters.myproject.controller.Task;

import eu.itcrafters.myproject.controller.Task.dto.TaskCreateRequest;
import eu.itcrafters.myproject.controller.Task.dto.TaskDto;
import eu.itcrafters.myproject.controller.Task.dto.TaskUpdateRequest;
import eu.itcrafters.myproject.service.Task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @PostMapping
    public TaskDto createTask(@Valid @RequestBody TaskCreateRequest request){
        return taskService.createTask(request);
    }

    @PatchMapping("/update/{id}")
    public TaskDto updateTask (@PathVariable Long id, @RequestBody TaskUpdateRequest request){
        return taskService.updateTask(id,request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}