package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;
import uz.pdp.sprimgbootlesson1.entity.StarBadge;
import uz.pdp.sprimgbootlesson1.payload.StarBadgeDto;
import uz.pdp.sprimgbootlesson1.repository.ProgLangRepository;
import uz.pdp.sprimgbootlesson1.repository.StarBadgeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    StarBadgeRepository starBadgeRepository;
    @Autowired
    ProgLangRepository progLangRepository;

    public List<StarBadge> getAll(){
        return starBadgeRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<StarBadge> starBadgeId = starBadgeRepository.findById(id);
        if (!starBadgeId.isPresent()) return new ApiResponse("Star badge not found", false, null);
        StarBadge starBadge = starBadgeId.get();
        return new ApiResponse("Star Badge found", true, starBadge);
    }
    public ApiResponse add(StarBadgeDto starBadgeDto){
        boolean exists = starBadgeRepository.existsByScore(starBadgeDto.getScore());
        if (exists) return new ApiResponse("This kind of Star Badge is exist", false, null);
        Optional<ProgLang> progLangId = progLangRepository.findById(starBadgeDto.getProgLangId());
        if (!progLangId.isPresent()) return new ApiResponse("Program language not found", false, null);
        StarBadge starBadge = new StarBadge();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(progLangId.get());
        StarBadge save = starBadgeRepository.save(starBadge);
        return new ApiResponse("Star Badge added", true, save);
    }
    public ApiResponse edit(Integer id, StarBadgeDto starBadgeDto){
        boolean exists = starBadgeRepository.existsByScoreAndIdNot(starBadgeDto.getScore(), id);
        if (exists) return new ApiResponse("This king of Star Badge is already exist", false, null);
        Optional<ProgLang> progLangId = progLangRepository.findById(starBadgeDto.getProgLangId());
        if (!progLangId.isPresent()) return new ApiResponse("Program Language not found", false, null);
        StarBadge starBadge = new StarBadge();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(progLangId.get());
        StarBadge save = starBadgeRepository.save(starBadge);
        return new ApiResponse("Star Badge edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<StarBadge> byId = starBadgeRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Star Badge not found", false, null);
        starBadgeRepository.deleteById(id);
        return new ApiResponse("Star Badge deleted", true, null);
    }
}
