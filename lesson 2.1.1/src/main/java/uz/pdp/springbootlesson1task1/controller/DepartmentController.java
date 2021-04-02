package uz.pdp.springbootlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Department;
import uz.pdp.springbootlesson1task1.payload.DepartmentDto;
import uz.pdp.springbootlesson1task1.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public HttpEntity<List<Department>> get(){
        List<Department> all = departmentService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse department = departmentService.getById(id);
        return ResponseEntity.status(department.isSuccess() ? 200 : 404).body(department);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse add = departmentService.add(departmentDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        ApiResponse edit = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 404).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = departmentService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
