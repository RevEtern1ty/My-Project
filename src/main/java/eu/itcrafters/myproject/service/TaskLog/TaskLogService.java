package eu.itcrafters.myproject.service.TaskLog;

import eu.itcrafters.myproject.persistence.TaskLog.TaskLogMapper;
import eu.itcrafters.myproject.persistence.TaskLog.TaskLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskLogService {

    private final TaskLogRepository taskLogRepository;
    private final TaskLogMapper taskLogMapper;


    public void getLogsByTaskId(Long taskId) {
        taskLogRepository.findByTaskId(taskId);
    }
}

