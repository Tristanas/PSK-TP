package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.GymBadge;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
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
    private String newBadgeName;

    @PostConstruct
    public void init() {
        this.trainer = trainersDAO.getCurrentTrainer();
    }

    @Transactional
    @LoggedInvocation
    public String createGymBadge() {
        GymBadge newBadge = new GymBadge();
        newBadge.setGymName(newBadgeName);
        badgeDAO.persist(newBadge);
        return "trainer?faces-redirect=true";
    }
}
