package uz.pdp.springbootlesson1task1.payload;

import lombok.Data;
import uz.pdp.springbootlesson1task1.entity.Address;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {
    @NotNull
    private String corpName;
    @NotNull
    private String directorName;

    private String street;
    private String homeNumber;
}
