package uz.pdp.springbootlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootlesson1task1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompanyId(String name, Integer company_id);
    boolean existsByNameAndCompanyIdAndIdNot(String name, Integer company_id, Integer id);
}
