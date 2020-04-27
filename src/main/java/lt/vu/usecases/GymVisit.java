package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;


// If this bean is set to @RequestScoped, PostConstruct will be called twice before the pokemon is caught.
// Thus an unexpected pokemon would be caught.
// Perhaps the pokemon was shown in one request and caught in a different one, thus a different pokemon was caught.
@Named
@ViewScoped
public class GymVisit implements Serializable {
    @Inject
    private PokemonDAO pokemonDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @Setter
    @Getter
    private Pokemon encounteredPokemon;

    @PostConstruct
    public void spawnPokemon()
    {
        System.out.println("A pokemon was encountered.");
        encounteredPokemon = pokemonDAO.getRandomPokemon();
    }

    @LoggedInvocation
    @Transactional
    public String catchPokemon()
    {
        Trainer trainer = trainersDAO.getCurrentTrainer();
        encounteredPokemon.setTrainer(trainer);
        pokemonDAO.persist(encounteredPokemon);
        System.out.println("A pokemon was caught.");
        trainer.gainXP(100);
        trainersDAO.update(trainer);
        return "trainer?faces-redirect=true";
    }
}
