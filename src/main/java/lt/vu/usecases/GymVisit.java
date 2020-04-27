package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pokemon;
import lt.vu.entities.Trainer;
import lt.vu.game.PokemonSpawn;
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
    private PokemonSpawn encounteredPokemon;

    @PostConstruct
    public void spawnPokemon()
    {
        System.out.println("A pokemon was encountered.");
        if (encounteredPokemon != null) return;
        encounteredPokemon = pokemonDAO.getSpawnedPokemon();
    }

    // For some reason PostConstruct is called for the second time before catchPokemon() is executed.
    @LoggedInvocation
    @Transactional
    public String catchPokemon()
    {
        Trainer trainer = trainersDAO.getCurrentTrainer();
        Pokemon caughtPokemon = new Pokemon();

        caughtPokemon.setCombatPower(encounteredPokemon.getCp());
        caughtPokemon.setNumber(encounteredPokemon.getNumber());
        caughtPokemon.setLevel(encounteredPokemon.getLevel());
        caughtPokemon.setName(encounteredPokemon.getName());
        caughtPokemon.setTrainer(trainer);
        pokemonDAO.persist(caughtPokemon);
        System.out.println("A pokemon was caught.");
        trainer.gainXP(100);
        trainersDAO.update(trainer);
        return "trainer?faces-redirect=true";
    }
}
