package uz.pdp.springbootlesson1task1.payload;

import lombok.Data;
import uz.pdp.springbootlesson1task1.entity.Company;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {
    @NotNull
    private String name;
    private Integer companyId;
}
