package uz.pdp.sprimgbootlesson1.payload;

import lombok.Data;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull
    private String name;
    private String description;
    private Integer progLangId;
}
