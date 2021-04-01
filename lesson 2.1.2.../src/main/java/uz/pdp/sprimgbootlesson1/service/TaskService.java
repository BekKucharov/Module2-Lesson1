package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.Category;
import uz.pdp.sprimgbootlesson1.entity.Task;
import uz.pdp.sprimgbootlesson1.payload.TaskDto;
import uz.pdp.sprimgbootlesson1.repository.CategoryRepository;
import uz.pdp.sprimgbootlesson1.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<Task> taskId = taskRepository.findById(id);
        if (!taskId.isPresent()) return new ApiResponse("Task not found", false, null);
        Task task = taskId.get();
        return new ApiResponse("Task found", true, task);
    }
    public ApiResponse add(TaskDto taskDto){
        boolean exists = taskRepository.existsByName(taskDto.getName());
        if (exists) return new ApiResponse("Task with this name is already exists", false, null);
        Optional<Category> categoryId = categoryRepository.findById(taskDto.getCategoryId());
        if (!categoryId.isPresent()) return new ApiResponse("Category not found", false, null);
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExamples(task.getExamples());
        task.setSolution(task.getSolution());
        task.setCategory(categoryId.get());
        Task save = taskRepository.save(task);
        return new ApiResponse("Task added successfully", true, save);
    }
    public ApiResponse edit(Integer id, TaskDto taskDto){
        Optional<Task> taskId = taskRepository.findById(id);
        if (!taskId.isPresent()) return new ApiResponse("Task not found", false, null);
        Optional<Category> categoryId = categoryRepository.findById(taskDto.getCategoryId());
        if (!categoryId.isPresent()) return new ApiResponse("Category not found", false, null);
        boolean exists = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (exists) return new ApiResponse("Task with this name is exists", false, null);
        Task task = taskId.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExamples(task.getExamples());
        task.setSolution(task.getSolution());
        task.setCategory(categoryId.get());
        Task save = taskRepository.save(task);
        return new ApiResponse("Task edited successfully", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<Task> taskId = taskRepository.findById(id);
        if (!taskId.isPresent()) return new ApiResponse("Task not found", false, null);
        taskRepository.deleteById(id);
        return new ApiResponse("Task deleted", true, null);
    }
}
