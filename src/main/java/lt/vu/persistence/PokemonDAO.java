package lt.vu.persistence;

import lt.vu.entities.Pokemon;
import lt.vu.services.PokemonSpawner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PokemonDAO {
    @Inject
    private EntityManager em;

    @Inject
    private PokemonSpawner spawner;

    public Pokemon getRandomPokemon() // public void setRandomPokemon()
    {
        return spawner.spawnRandomPokemon();
    }

    public void update(Pokemon pokemon) { em.merge(pokemon);}

    public void persist(Pokemon pokemon) { em.persist(pokemon);}

    public Pokemon findOne(Integer id) {return em.find(Pokemon.class, id);}

    public void remove(Pokemon pokemon) {
        // Making sure the entity is in managed state.
        pokemon = em.merge(pokemon);
        em.remove(pokemon);
    }
}
