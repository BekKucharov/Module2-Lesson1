package uz.pdp.springbootlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootlesson1task1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpNameAndDirectorName(String corpName, String directorName);
    boolean existsByCorpNameAndDirectorNameAndIdNot(String corpName, String directorName, Integer id);
}
