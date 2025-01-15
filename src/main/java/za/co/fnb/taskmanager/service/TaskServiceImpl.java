package za.co.fnb.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.fnb.taskmanager.domain.Task;
import za.co.fnb.taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public Task update(Long id, Task taskRecordToSave) {

        Task taskRecord = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        taskRecord.update(taskRecordToSave);

       return taskRecord;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
