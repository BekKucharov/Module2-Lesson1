package uz.pdp.sprimgbootlesson1.payload;

import lombok.Data;
import uz.pdp.sprimgbootlesson1.entity.Category;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull
    private String name;
    @NotNull
    private String text;
    private String examples;
    private String solution;
    private Integer categoryId;
}
