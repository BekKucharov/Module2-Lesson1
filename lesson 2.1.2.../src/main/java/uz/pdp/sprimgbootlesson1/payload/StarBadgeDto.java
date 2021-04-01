package uz.pdp.sprimgbootlesson1.payload;

import lombok.Data;
import uz.pdp.sprimgbootlesson1.entity.ProgLang;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@Data
public class StarBadgeDto {
    @NotNull
    private Integer score;
    private Integer progLangId;
}
