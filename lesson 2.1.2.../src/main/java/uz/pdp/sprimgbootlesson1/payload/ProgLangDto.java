package uz.pdp.sprimgbootlesson1.payload;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ProgLangDto {
    @NotNull
    private String name;

    private String description;


}
