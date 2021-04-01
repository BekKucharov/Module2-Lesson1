package uz.pdp.sprimgbootlesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sprimgbootlesson1.entity.Answer;
import uz.pdp.sprimgbootlesson1.entity.ApiResponse;
import uz.pdp.sprimgbootlesson1.entity.Task;
import uz.pdp.sprimgbootlesson1.entity.User;
import uz.pdp.sprimgbootlesson1.payload.AnswerDto;
import uz.pdp.sprimgbootlesson1.repository.AnswerRepository;
import uz.pdp.sprimgbootlesson1.repository.TaskRepository;
import uz.pdp.sprimgbootlesson1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Answer> getAll(){
        return answerRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<Answer> answerId = answerRepository.findById(id);
        if (!answerId.isPresent()) return new ApiResponse("Answer not found", false, null);
        Answer answer = answerId.get();
        return new ApiResponse("Answer found", true, answer);
    }
    public ApiResponse add(AnswerDto answerDto){
        boolean exists = answerRepository.existsByBody(answerDto.getBody());
        if (exists) return new ApiResponse("This answer is exists", false, null);
        Optional<User> userId = userRepository.findById(answerDto.getUserId());
        if (!userId.isPresent()) return new ApiResponse("User not found", false, null);
        Optional<Task> taskId = taskRepository.findById(answerDto.getTaskId());
        if (!taskId.isPresent()) return new ApiResponse("Task not found", false, null);

        Answer answer = new Answer();
        answer.setBody(answer.getBody());
        answer.setTask(taskId.get());
        answer.setUser(userId.get());
        Answer save = answerRepository.save(answer);
        return new ApiResponse("Answer added", true, save);
    }
    public ApiResponse edit(Integer id, AnswerDto answerDto){
        Optional<Answer> answerId = answerRepository.findById(id);
        if (!answerId.isPresent()) return new ApiResponse("Answer not found", false, null);
        boolean exists = answerRepository.existsByBodyAndIdNot(answerDto.getBody(), id);
        if (exists) return new ApiResponse("This answer is exists", false,null);
        Optional<Task> taskId = taskRepository.findById(answerDto.getTaskId());
        if (!taskId.isPresent()) return new ApiResponse("Task not found", false, null);
        Optional<User> userId = userRepository.findById(answerDto.getUserId());
        if (!userId.isPresent()) return new ApiResponse("User not found", false, null);

        Answer answer = answerId.get();
        answer.setBody(answer.getBody());
        answer.setTask(taskId.get());
        answer.setUser(userId.get());
        Answer save = answerRepository.save(answer);
        return new ApiResponse("Answer edited", true, save);
    }
    public ApiResponse delete(Integer id){
        Optional<Answer> answerId = answerRepository.findById(id);
        if (!answerId.isPresent()) return new ApiResponse("Answer not found", false, null);
        answerRepository.deleteById(id);
        return new ApiResponse("Answer deleted", true, null);
    }
}
