package za.co.fnb.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.fnb.taskmanager.domain.Task;

public interface TaskRepository
        extends JpaRepository<Task, Long>{
}
