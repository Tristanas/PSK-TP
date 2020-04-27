package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Trainer {

    private Integer id;
    private Integer level;
    private String username;
    private Integer xp;

    private List<Pokemon> pokemonList;
    private List<Gymbadge> gymbadges;
}