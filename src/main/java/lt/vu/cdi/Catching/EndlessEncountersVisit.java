package lt.vu.cdi.Catching;

import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.enterprise.inject.Alternative;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@Alternative
@ViewScoped
public class EndlessEncountersVisit implements ICatchingActivity, Serializable {

    @Inject
    private PokemonDAO pokemonDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @LoggedInvocation
    @Transactional
    public String catchPokemon(Pokemon pokemon, Trainer trainer)
    {
        pokemon.setTrainer(trainer);
        pokemonDAO.persist(pokemon);
        System.out.println("A pokemon was caught.");
        trainersDAO.giveXP(100, trainer);
        return "gymVisit?faces-redirect=true";
    }
}
