package lt.vu.game;

import lombok.Getter;
import lt.vu.entities.Pokemon;

public class PokemonSpawn {
    @Getter
    private int number;
    @Getter
    private int level;
    @Getter
    private int cp;
    @Getter
    private String name;

    public PokemonSpawn(int number, int level, int cp, String name){
        this.name = name;
        this.number = number;
        this.level = level;
        this.cp = cp;
    }
}
