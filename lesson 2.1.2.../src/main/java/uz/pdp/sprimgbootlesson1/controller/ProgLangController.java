package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;
import uz.pdp.sprimgbootlesson1.payload.ProgLangDto;
import uz.pdp.sprimgbootlesson1.service.ProgLangService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/proglang")
public class ProgLangController {
    @Autowired
    ProgLangService progLangService;

    @GetMapping
    public ResponseEntity<List<ProgLang>> getAll(){

        List<ProgLang> all = progLangService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        ApiResponse byId = progLangService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(byId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody ProgLangDto progLangDto){
        ApiResponse add = progLangService.add(progLangDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody ProgLangDto progLangDto){
        ApiResponse edit = progLangService.edit(id, progLangDto);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse delete = progLangService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }
}
