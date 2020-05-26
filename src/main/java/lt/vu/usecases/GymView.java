package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.GymBadge;
import lt.vu.entities.Trainer;
import lt.vu.cdi.interceptors.LoggedInvocation;
import lt.vu.persistence.GymBadgeDAO;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class GymView implements Serializable {
    @Inject
    private GymBadgeDAO badgeDAO;

    @Inject
    private TrainersDAO trainersDAO;

    @Setter
    @Getter
    private Trainer currentTrainer;

    @Setter
    @Getter
    private GymBadge gym;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer badgeId;

        try {
            badgeId  = Integer.parseInt(requestParameters.get("gymId"));
            gym = badgeDAO.findOne(badgeId);
        } catch (NumberFormatException e) {
            System.out.print("Redirect occurred.");
        }
        currentTrainer = trainersDAO.getCurrentTrainer();
    }

    @Transactional
    @LoggedInvocation
    public String RenameGym() {
        System.out.println("Editing gym: " + gym.getGymName() + " | version is: " + gym.getVersion());
        try{
            badgeDAO.update(this.gym);
        } catch (OptimisticLockException e) {
            System.out.println("Editing: Optimistic lock exception");
            return "/gym.xhtml?faces-redirect=true&gymId=" + this.gym.getId() + "&error=optimistic-lock-exception";
        }
        return "gym?faces-redirect=true&gymId=" + gym.getId();
    }


}
