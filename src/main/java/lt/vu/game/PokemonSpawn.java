package lt.vu.game;

import lombok.Getter;
import lombok.Setter;
import lt.vu.qualifiers.RandomCP;
import lt.vu.qualifiers.RandomLevel;

import javax.inject.Inject;

public class PokemonSpawn {
    @Getter @Setter
    private int number;
    @Getter
    private int level;
    @Getter
    private int cp;
    @Getter @Setter
    private String name;

    @Inject
    public PokemonSpawn(@RandomLevel int level, @RandomCP int cp){
        this.level = level;
        this.cp = cp + level * 24;
    }
}
