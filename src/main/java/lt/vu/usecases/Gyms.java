package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.GymBadge;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.GymBadgeDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Gyms {
    @Inject
    private GymBadgeDAO badgeDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @Setter
    @Getter
    private Trainer currentTrainer;

    @Setter
    @Getter
    private List<GymBadge> gyms;

    @PostConstruct
    public void init(){
        gyms = badgeDAO.list();
        currentTrainer = trainersDAO.getCurrentTrainer();
    }

    @Transactional
    @LoggedInvocation
    public String visitGym(GymBadge gymBadge)
    {
        // Adds a new gym badge, if this is the first visit.
        trainersDAO.addGymBadge(gymBadge, currentTrainer);
        currentTrainer.gainXP(50);
        trainersDAO.update(currentTrainer);
        // Could add additional code to add pokemon and possibly items.
        return "gymVisit?faces-redirect=true";
    }


}
