package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.StarBadge;
import uz.pdp.sprimgbootlesson1.entity.User;
import uz.pdp.sprimgbootlesson1.payload.UserDto;
import uz.pdp.sprimgbootlesson1.repository.StarBadgeRepository;
import uz.pdp.sprimgbootlesson1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<User> userId = userRepository.findById(id);
        if (!userId.isPresent()) return new ApiResponse("User not found", false, null);
        User user = userId.get();
        return new ApiResponse("User found", true, user);
    }
    public ApiResponse add(UserDto userDto){
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists) return new ApiResponse("User with this email is exists", false, null);
        Optional<StarBadge> starBadgeId = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!starBadgeId.isPresent()) return new ApiResponse("Star Badge not found", false, null);
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setStarBadge(starBadgeId.get());
        User save = userRepository.save(user);
        return new ApiResponse("User added", true, save);
    }
    public ApiResponse edit(Integer id, UserDto userDto){
        boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (exists) return new ApiResponse("User with this email is exists", false, null);
        Optional<User> userId = userRepository.findById(id);
        if (!userId.isPresent()) return new ApiResponse("User not found", false, null);
        Optional<StarBadge> starBadgeId = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!starBadgeId.isPresent()) return new ApiResponse("Star Badge not found", false, null);
        User user = userId.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setStarBadge(starBadgeId.get());
        User save = userRepository.save(user);
        return new ApiResponse("User edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<User> userId = userRepository.findById(id);
        if (!userId.isPresent()) return new ApiResponse("User not found", false, null);
        userRepository.deleteById(id);
        return new ApiResponse("User deleted", true, null);
    }
}
