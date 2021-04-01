package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.Category;
import uz.pdp.sprimgbootlesson1.payload.CategoryDto;
import uz.pdp.sprimgbootlesson1.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<List<Category>> getAll(){
        List<Category> all = categoryService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        ApiResponse byId = categoryService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(byId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse add = categoryService.add(categoryDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        ApiResponse edit = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = categoryService.del(id);
        return ResponseEntity.status(del.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(del);
    }

}
