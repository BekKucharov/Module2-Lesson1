package uz.pdp.springbootlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Company;
import uz.pdp.springbootlesson1task1.payload.CompanyDto;
import uz.pdp.springbootlesson1task1.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public HttpEntity<List<Company>> get(){
        List<Company> all = companyService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse companyId = companyService.getById(id);
        return ResponseEntity.status(companyId.isSuccess() ? 200 : 404).body(companyId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse add = companyService.add(companyDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse edit = companyService.edit(id, companyDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 404).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = companyService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
