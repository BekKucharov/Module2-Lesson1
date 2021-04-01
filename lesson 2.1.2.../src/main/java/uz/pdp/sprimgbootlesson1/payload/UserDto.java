package uz.pdp.sprimgbootlesson1.payload;

import lombok.Data;
import uz.pdp.sprimgbootlesson1.entity.StarBadge;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Integer starBadgeId;
}
