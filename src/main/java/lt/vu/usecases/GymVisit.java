package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pokemon;
import lt.vu.cdi.Catching.ICatchingActivity;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.game.PokemonSpawn;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;


// If this bean is set to @RequestScoped, PostConstruct will be called twice before the pokemon is caught.
// Thus an unexpected pokemon would be caught.
// Perhaps the pokemon was shown in one request and caught in a different one, thus a different pokemon was caught.
@Named
@SessionScoped
public class GymVisit implements Serializable {
    @Inject
    private PokemonDAO pokemonDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @Inject
    private ICatchingActivity catchingActivity;

    @Setter
    @Getter
    private Pokemon encounteredPokemon;

    @PostConstruct
    public void spawnPokemon()
    {
        encounteredPokemon = pokemonDAO.getRandomPokemon();
    }

    @PreDestroy
    public void goodBye(){
        System.out.print("Gym visit ended.");
    }

    @LoggedInvocation
    @Transactional
    public String catchPokemon()
    {
        String navigation_path = catchingActivity.catchPokemon(encounteredPokemon, trainersDAO.getCurrentTrainer());
        spawnPokemon();
        return navigation_path;
    }
}
