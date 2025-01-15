package za.co.fnb.taskmanager.service;

import za.co.fnb.taskmanager.domain.Task;

import java.util.List;

public interface TaskService {

    void deleteById(Long taskId);

    Task createTask(Task task);

    Task update(Long id, Task taskRecordToSave) throws Exception;

    List<Task> findAll();

}
