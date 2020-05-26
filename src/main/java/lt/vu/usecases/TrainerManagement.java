package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.GymBadge;
import lt.vu.entities.Trainer;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.persistence.GymBadgeDAO;
import lt.vu.persistence.PokemonDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class TrainerManagement {
    @Inject
    private GymBadgeDAO badgeDAO;

    @Inject
    private PokemonDAO pokemonDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @Setter
    @Getter
    private Trainer trainer;

    @Getter
    @Setter
    private GymBadge newBadge;

    @PostConstruct
    public void init() {
        this.trainer = trainersDAO.getCurrentTrainer();
        System.out.println("PostConstruct was called");
        newBadge = new GymBadge();
    }

    @Transactional
    @LoggedInvocation
    public String createGymBadge() {
        badgeDAO.persist(newBadge);
        System.out.println("Creating a new gym badge: " + newBadge.getGymName());
        return "trainer?faces-redirect=true";
    }
}
