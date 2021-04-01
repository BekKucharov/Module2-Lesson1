package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.Category;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;
import uz.pdp.sprimgbootlesson1.payload.CategoryDto;
import uz.pdp.sprimgbootlesson1.repository.CategoryRepository;
import uz.pdp.sprimgbootlesson1.repository.ProgLangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProgLangRepository progLangRepository;
    public List<Category> getAll(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }
    public ApiResponse getById(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Category not found", false, null);
        Category category = byId.get();
        return new ApiResponse("Category found", true, category);
    }
    public ApiResponse add(CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) return new ApiResponse("This king of category is already exists", false, null);
        Optional<ProgLang> progLangId = progLangRepository.findById(categoryDto.getProgLangId());
        if (!progLangId.isPresent()) return new ApiResponse("Program language not found", false, null);
        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        category.setProgLang(progLangId.get());
        Category save = categoryRepository.save(category);
        return new ApiResponse("Category added successful", true, save);
    }
    public ApiResponse edit(Integer id, CategoryDto categoryDto){
        boolean exists = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (exists) return new ApiResponse("This king of category is already exists", false, null);
        Optional<Category> categoryId = categoryRepository.findById(id);
        if (!categoryId.isPresent()) return new ApiResponse("Category not found", false, null);
        Optional<ProgLang> progLangId = progLangRepository.findById(categoryDto.getProgLangId());
        if (!progLangId.isPresent()) return new ApiResponse("Program language not found", false, null);
        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        category.setProgLang(progLangId.get());
        Category save = categoryRepository.save(category);
        return new ApiResponse("Category edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Category not found", false, null);
        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted", true, null);
    }
}
