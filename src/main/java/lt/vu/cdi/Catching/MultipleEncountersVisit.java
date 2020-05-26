package lt.vu.cdi.Catching;

import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@Alternative
@SessionScoped
public class MultipleEncountersVisit implements ICatchingActivity, Serializable {
    @Inject
    private PokemonDAO pokemonDAO;

    @Inject
    private TrainersDAO trainersDAO;

    private Integer encounters = 2;

    @LoggedInvocation
    @Transactional
    public String catchPokemon(Pokemon encounteredPokemon, Trainer trainer)
    {
        encounteredPokemon.setTrainer(trainer);
        pokemonDAO.persist(encounteredPokemon);
        System.out.println("A pokemon was caught.");
        trainersDAO.giveXP(100, trainer);
        encounters--;
        System.out.println("Caught a pokemon, can catch: " + encounters + " more.");
        if (encounters > 0)
            return "gymVisit?faces-redirect=false";
        else {
            encounters = 2;
            return "trainer?faces-redirect=true";
        }

    }
}
