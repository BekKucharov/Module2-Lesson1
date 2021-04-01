package uz.pdp.sprimgbootlesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sprimgbootlesson1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
