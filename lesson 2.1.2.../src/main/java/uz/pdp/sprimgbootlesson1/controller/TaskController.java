package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.Task;
import uz.pdp.sprimgbootlesson1.payload.TaskDto;
import uz.pdp.sprimgbootlesson1.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public HttpEntity<List<Task>> getAll(){
        List<Task> all = taskService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        ApiResponse byId = taskService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? 200 : 404).body(byId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody TaskDto taskDto){
        ApiResponse add = taskService.add(taskDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody TaskDto taskDto){
        ApiResponse edit = taskService.edit(id, taskDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = taskService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
