package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;
import uz.pdp.sprimgbootlesson1.payload.ProgLangDto;
import uz.pdp.sprimgbootlesson1.repository.ProgLangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgLangService {
    @Autowired
    ProgLangRepository progLangRepository;

    public List<ProgLang> getAll() {
        return progLangRepository.findAll();
    }

    public ApiResponse getById(Integer id) {
        Optional<ProgLang> progLangId = progLangRepository.findById(id);
        if (!progLangId.isPresent()) return new ApiResponse("Program language not found", false, null);
        ProgLang progLang = progLangId.get();
        return new ApiResponse("Program language found", true, progLang);
    }

    public ApiResponse add(ProgLangDto progLangDto) {
        boolean exists = progLangRepository.existsByName(progLangDto.getName());
        if (exists)
            return new ApiResponse("This kind of language is already exists", false, null);
        ProgLang progLang = new ProgLang();
        progLang.setName(progLangDto.getName());
        progLang.setDescription(progLangDto.getDescription());
        ProgLang save = progLangRepository.save(progLang);
        return new ApiResponse("Program Language created", true, save);
    }

    public ApiResponse edit(Integer id, ProgLangDto progLangDto) {
        Optional<ProgLang> progLangId = progLangRepository.findById(id);
        if (!progLangId.isPresent())
            return new ApiResponse("Program language not found", false, null);
        boolean exists = progLangRepository.existsByNameAndIdNot(progLangDto.getName(), id);
        if (exists)
            return new ApiResponse("This kind of language is already exists", false, null);

        ProgLang progLang = progLangId.get();
        progLang.setName(progLangDto.getName());
        progLang.setDescription(progLangDto.getDescription());
        ProgLang save = progLangRepository.save(progLang);
        return new ApiResponse("Program language edited", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<ProgLang> progLangId = progLangRepository.findById(id);
        if (!progLangId.isPresent())
            return new ApiResponse("Program language not found", false, null);
        progLangRepository.deleteById(id);
        return new ApiResponse("Program language deleted", true, null);
    }
}
