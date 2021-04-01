package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.Answer;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.payload.AnswerDto;
import uz.pdp.sprimgbootlesson1.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public HttpEntity<List<Answer>> get(){
        List<Answer> all = answerService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> get(@PathVariable Integer id){
        ApiResponse answerId = answerService.getById(id);
        return ResponseEntity.status(answerId.isSuccess() ? 200 : 404).body(answerId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse add = answerService.add(answerDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody AnswerDto answerDto){
        ApiResponse edit = answerService.edit(id, answerDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse delete = answerService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 404).body(delete);
    }
}
