package lt.vu.persistence;

import lt.vu.entities.Pokemon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PokemonDAO {
    @Inject
    private EntityManager em;

    private void update(Pokemon pokemon) { em.merge(pokemon);}

    private void persist(Pokemon pokemon) { em.persist(pokemon);}

    private Pokemon findOne(Integer id) {return em.find(Pokemon.class, id);}
}
