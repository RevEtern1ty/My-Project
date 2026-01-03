package eu.itcrafters.myproject.persistence.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskCode(String taskCode);
    List<Task> findByEmployeeId(Long employeeId);
    List<Task> findByWorkstationId(Long workstationId);
    List<Task> findAllByStatus(String status);
}
