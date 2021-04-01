package uz.pdp.sprimgbootlesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sprimgbootlesson1.entity.StarBadge;

public interface StarBadgeRepository extends JpaRepository<StarBadge, Integer> {
    boolean existsByScore(Integer score);
    boolean existsByScoreAndIdNot(Integer score, Integer id);
}
