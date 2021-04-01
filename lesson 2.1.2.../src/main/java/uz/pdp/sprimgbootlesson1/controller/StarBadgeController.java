package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.StarBadge;
import uz.pdp.sprimgbootlesson1.payload.StarBadgeDto;
import uz.pdp.sprimgbootlesson1.service.StarBadgeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/starbadge")
public class StarBadgeController {
    @Autowired
    StarBadgeService starBadgeService;

    @GetMapping
    public HttpEntity<List<StarBadge>> getAll(){
        List<StarBadge> all = starBadgeService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        ApiResponse byId = starBadgeService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? 200 : 404).body(byId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody StarBadgeDto starBadgeDto){
        ApiResponse add = starBadgeService.add(starBadgeDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody StarBadgeDto starBadgeDto){
        ApiResponse edit = starBadgeService.edit(id, starBadgeDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = starBadgeService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
