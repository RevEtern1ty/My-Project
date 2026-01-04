package eu.itcrafters.myproject.service.Task;

import eu.itcrafters.myproject.persistence.Task.TaskMapper;
import eu.itcrafters.myproject.persistence.Task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public  void getAllTasks(){
       taskRepository.findAll();
    }


}
