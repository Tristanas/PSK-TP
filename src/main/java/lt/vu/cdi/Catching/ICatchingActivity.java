package lt.vu.cdi.Catching;

import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;

public interface ICatchingActivity {
    String catchPokemon(Pokemon pokemon, Trainer trainer);
}
