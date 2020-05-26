package lt.vu.services;

import lt.vu.entities.Pokemon;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class PokemonSpawner {

    protected String[] pokemonNames = {"Pikachu", "Charizard", "Squirtle", "Bulbasaur",
            "Weedle", "Ratata", "Pidgey", "Wurmple"};

    public Pokemon spawnRandomPokemon() // public void setRandomPokemon()
    {
        Random rand = new Random();
        Pokemon randomPokemon = new Pokemon();
        int i = rand.nextInt(pokemonNames.length);
        int randomLevel = rand.nextInt(25);
        randomPokemon.setNumber(i + 1);
        randomPokemon.setLevel(randomLevel + 1);
        randomPokemon.setCombatPower((randomLevel * 24) + rand.nextInt(100));
        randomPokemon.setName(pokemonNames[i]);
        return randomPokemon;
    }
}
