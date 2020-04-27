package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class GymVisit {
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

    // For some reason PostConstruct is called for the second time before catchPokemon() is executed.
    // Probably because this bean is initialized once the page is loaded and again when the button is clicked due to the references.
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
        pokemonDAO.setRandomPokemon();
        return "trainer?faces-redirect=true";
    }
}
