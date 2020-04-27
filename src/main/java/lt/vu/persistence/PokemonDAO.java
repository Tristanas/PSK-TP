package lt.vu.persistence;

import lt.vu.entities.Pokemon;
import lt.vu.game.PokemonSpawn;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PokemonDAO {
    @Inject
    private EntityManager em;

    private Random rand;

    String[] pokemonNames = {"Pikachu", "Charizard", "Squirtle", "Bulbasaur",
            "Weedle", "Ratata", "Pidgey", "Wurmple"};

    public PokemonSpawn getSpawnedPokemon()
    {
        rand = new Random();
        int i = rand.nextInt(pokemonNames.length);
        int randomLevel = 1 + rand.nextInt(24);
        return new PokemonSpawn(i + 1,
                randomLevel,
                (int)(randomLevel * 24) + rand.nextInt(100),
                pokemonNames[i]);
    }

    public void update(Pokemon pokemon) { em.merge(pokemon);}

    public void persist(Pokemon pokemon) { em.persist(pokemon);}

    public Pokemon findOne(Integer id) {return em.find(Pokemon.class, id);}
}
