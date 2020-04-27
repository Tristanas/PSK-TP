package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pokemon {

    private Integer id;
    private Integer combatpower;
    private Integer level;
    private String name;
    private Integer number;
    private Integer trainerId;

    private Trainer trainer;
}