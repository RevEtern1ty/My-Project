package eu.itcrafters.myproject.controller.TaskLog;


import eu.itcrafters.myproject.service.TaskLog.TaskLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskLogController {

    private final TaskLogService taskLogService;

    @GetMapping("/{taskId}logs")
    public void getTaskLogs(@PathVariable Long taskId){
        taskLogService.getLogsByTaskId(taskId);
    }
}
