package za.co.fnb.taskmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import za.co.fnb.taskmanager.domain.Task;
import za.co.fnb.taskmanager.repository.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    public static final String TASK_1_TEXT = "testing task number 1";
    public static final String UPDATED_TASK_1_TEXT = "Updated task 1";

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteById() {
        Task task = Task.builder().text(TASK_1_TEXT).id(1L).build();

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        taskRepository.save(task);

        taskService.deleteById(1L);

        List tasks = taskRepository.findAll();

        assertAll(() -> assertNotNull(tasks),
                () -> assertTrue(tasks.isEmpty()));

    }

    @Test
    void createTask() {
        Task task = Task.builder().text(TASK_1_TEXT).id(1L).build();

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.createTask(task);

        assertAll(() -> assertNotNull(savedTask),
                () -> assertEquals(TASK_1_TEXT, savedTask.getText()),
                () -> assertEquals(1L, savedTask.getId()));
    }

    @Test
    void update() {
        final Task task = Task.builder().text(TASK_1_TEXT).id(1L).build();

        Mockito.when(taskRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(task));

        Task changedTask = Task.builder().id(1L).text(UPDATED_TASK_1_TEXT).build();

        Task updatedTask = taskService.update(1L, changedTask);

        assertAll(() -> assertNotNull(updatedTask),
                () -> assertEquals(UPDATED_TASK_1_TEXT, updatedTask.getText()),
                () -> assertEquals(1L, updatedTask.getId()));
    }

    @Test
    void findAll() {
        Task task = Task.builder().text("testing 1234").id(1L).build();

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.createTask(task);

        assertAll(() -> assertNotNull(savedTask),
                  () -> assertEquals("testing 1234", savedTask.getText()),
                  () -> assertEquals(1L, savedTask.getId()));

    }
}