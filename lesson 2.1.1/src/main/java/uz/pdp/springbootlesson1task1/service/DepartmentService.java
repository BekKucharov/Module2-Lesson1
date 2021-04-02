package uz.pdp.springbootlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Company;
import uz.pdp.springbootlesson1task1.entity.Department;
import uz.pdp.springbootlesson1task1.payload.DepartmentDto;
import uz.pdp.springbootlesson1task1.repository.CompanyRepository;
import uz.pdp.springbootlesson1task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<Department> departmentId = departmentRepository.findById(id);
        if (!departmentId.isPresent()) return new ApiResponse("Department not found", false, null);
        Department department = departmentId.get();
        return new ApiResponse("Department found", true, department);
    }
    public ApiResponse add(DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());
        if (exists) return new ApiResponse("Department is already exists", false, null);
        Department department = new Department();
        department.setName(departmentDto.getName());

        Optional<Company> companyId = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyId.isPresent()) return new ApiResponse("Company not found", false, null);
        department.setCompany(companyId.get());
        Department save = departmentRepository.save(department);
        return new ApiResponse("Department added", true, save);
    }
    public ApiResponse edit(Integer id, DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(), id);
        if (exists) return new ApiResponse("Department is already exists", false, null);
        Optional<Department> departmentId = departmentRepository.findById(id);
        if (!departmentId.isPresent()) return new ApiResponse("Department not found", false, null);
        Optional<Company> companyId = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyId.isPresent()) return new ApiResponse("Company not found", false, null);
        Department department = departmentId.get();
        department.setName(departmentDto.getName());
        department.setCompany(companyId.get());
        Department save = departmentRepository.save(department);
        return new ApiResponse("Department edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<Department> departmentId = departmentRepository.findById(id);
        if (!departmentId.isPresent()) return new ApiResponse("Department not found", false, null);
        departmentRepository.deleteById(id);
        return new ApiResponse("Department deleted", true, null);
    }
}
