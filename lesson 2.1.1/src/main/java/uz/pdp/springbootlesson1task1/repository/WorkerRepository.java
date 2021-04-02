package uz.pdp.springbootlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootlesson1task1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);
    boolean existsByNameAndPhoneNumberAndIdNot(String name, String phoneNumber, Integer id);
}
