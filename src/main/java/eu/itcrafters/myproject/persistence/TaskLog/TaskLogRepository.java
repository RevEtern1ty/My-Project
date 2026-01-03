package eu.itcrafters.myproject.persistence.TaskLog;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskLogRepository extends JpaRepository<TaskLog, Long> {
    List<TaskLog> findByTaskId(Long taskId);
}

