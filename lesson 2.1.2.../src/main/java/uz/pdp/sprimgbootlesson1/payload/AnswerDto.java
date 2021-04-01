package uz.pdp.sprimgbootlesson1.payload;

import lombok.Data;
import uz.pdp.sprimgbootlesson1.entity.Task;
import uz.pdp.sprimgbootlesson1.entity.User;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @NotNull
    private String body;
    private Integer taskId;
    private Integer userId;
}
