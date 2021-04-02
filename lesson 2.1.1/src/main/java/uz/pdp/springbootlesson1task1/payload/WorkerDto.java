package uz.pdp.springbootlesson1task1.payload;

import lombok.Data;
import uz.pdp.springbootlesson1task1.entity.Department;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    private Integer departmentId;

    private String street;
    private String homeNumber;
}
