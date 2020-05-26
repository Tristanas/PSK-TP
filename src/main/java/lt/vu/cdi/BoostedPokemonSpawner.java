package lt.vu.cdi;

import lt.vu.entities.Pokemon;
import lt.vu.services.PokemonSpawner;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
@ApplicationScoped
public class BoostedPokemonSpawner extends PokemonSpawner {
    public Pokemon spawnRandomPokemon() // public void setRandomPokemon()
    {
        Random rand = new Random();
        Pokemon randomPokemon = new Pokemon();
        int i = rand.nextInt(pokemonNames.length);
        int randomLevel = rand.nextInt(25) + 11;
        randomPokemon.setNumber(i + 1);
        randomPokemon.setLevel(randomLevel + 1);
        randomPokemon.setCombatPower((randomLevel * 24) + rand.nextInt(100));
        randomPokemon.setName(pokemonNames[i]);
        return randomPokemon;
    }

}
