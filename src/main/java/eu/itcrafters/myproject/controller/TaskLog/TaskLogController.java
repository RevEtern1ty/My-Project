package eu.itcrafters.myproject.controller.TaskLog;


import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogCreateRequest;
import eu.itcrafters.myproject.controller.TaskLog.dto.TaskLogDto;
import eu.itcrafters.myproject.service.TaskLog.TaskLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task-logs")
public class TaskLogController {

    private final TaskLogService taskLogService;

    @GetMapping("/task/{taskId}")
    public List<TaskLogDto> getLogsByTask(@PathVariable Long taskId) {
        return taskLogService.getLogsByTaskId(taskId);
    }

    @PostMapping("/create")
    public TaskLogDto createLog(@Valid @RequestBody TaskLogCreateRequest request) {
        return taskLogService.createLog(request);
    }
}
