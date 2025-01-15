package za.co.fnb.taskmanager.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.fnb.taskmanager.domain.Task;
import za.co.fnb.taskmanager.service.TaskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/operations/v1")
public class OperationsV1  implements ApiService{
    private final TaskService taskService;

    @Autowired
    public OperationsV1(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/findAll",produces = "application/json")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> results = taskService.findAll();

        return ResponseEntity.ok(results);
    }

    @PostMapping(path = "/save",produces = "application/json")
    public ResponseEntity<Task> save(@RequestBody Task task) {
        Task result = taskService.createTask(task);

        return ResponseEntity.ok(result);
    }

    @PatchMapping(path = "/update/{id}",produces = "application/json")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task taskRecordToSave) {

        try {
            Task result = taskService.update(id, taskRecordToSave);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred ", e);

            return null;
        }
    }

    @DeleteMapping(path = "/task/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long taskId) {
        taskService.deleteById(taskId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    @Override
    public String getBasePath() {
        return "operations/v1";
    }
}
