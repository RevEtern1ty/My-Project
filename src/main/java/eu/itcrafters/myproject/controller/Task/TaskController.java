package eu.itcrafters.myproject.controller.Task;

import eu.itcrafters.myproject.service.Task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public void getAllTasks(){
        taskService.getAllTasks();
    }
}