package uz.pdp.sprimgbootlesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sprimgbootlesson1.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByBody(String body);
    boolean existsByBodyAndIdNot(String body, Integer id);
}
