package lt.vu.persistence;

import lt.vu.entities.Pokemon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Random;

@ApplicationScoped
public class PokemonDAO {
    @Inject
    private EntityManager em;

//    private Pokemon randomPokemon = null;

    String[] pokemonNames = {"Pikachu", "Charizard", "Squirtle", "Bulbasaur",
            "Weedle", "Ratata", "Pidgey", "Wurmple"};

//    public Pokemon getRandomPokemon()
//    {
//        if (randomPokemon == null)
//            setRandomPokemon();
//        return randomPokemon;
//    }

    public Pokemon getRandomPokemon() // public void setRandomPokemon()
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

    public void update(Pokemon pokemon) { em.merge(pokemon);}

    public void persist(Pokemon pokemon) { em.persist(pokemon);}

    public Pokemon findOne(Integer id) {return em.find(Pokemon.class, id);}

    public void remove(Pokemon pokemon) { em.remove(pokemon);}
}
