package uz.pdp.sprimgbootlesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.User;
import uz.pdp.sprimgbootlesson1.payload.UserDto;
import uz.pdp.sprimgbootlesson1.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<List<User>> get(){
        List<User> all = userService.getAll();
        return ResponseEntity.status(200).body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        ApiResponse userId = userService.getById(id);
        return ResponseEntity.status(userId.isSuccess() ? 200 : 404).body(userId);
    }
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid  @RequestBody UserDto userDto){
        ApiResponse add = userService.add(userDto);
        return ResponseEntity.status(add.isSuccess() ? 201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @PathVariable Integer id, @RequestBody UserDto userDto){
        ApiResponse edit = userService.edit(id, userDto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 404).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del(@PathVariable Integer id){
        ApiResponse del = userService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 200 : 404).body(del);
    }
}
