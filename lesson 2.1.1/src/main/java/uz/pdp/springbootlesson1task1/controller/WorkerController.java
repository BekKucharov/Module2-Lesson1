package uz.pdp.springbootlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Worker;
import uz.pdp.springbootlesson1task1.payload.WorkerDto;
import uz.pdp.springbootlesson1task1.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public HttpEntity<List<Worker>> get() {
        List<Worker> workers = workerService.get();
        return ResponseEntity.status(200).body(workers);
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> get(@PathVariable Integer id) {
        ApiResponse worker = workerService.getById(id);
        return ResponseEntity.status(worker.isSuccess() ? 200 : 404).body(worker);
    }

    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse add = workerService.add(workerDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        ApiResponse edit = workerService.edit(id, workerDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 404).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id) {
        ApiResponse del = workerService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
