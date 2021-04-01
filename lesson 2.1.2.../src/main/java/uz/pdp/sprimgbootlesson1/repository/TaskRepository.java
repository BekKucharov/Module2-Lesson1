package uz.pdp.sprimgbootlesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sprimgbootlesson1.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
