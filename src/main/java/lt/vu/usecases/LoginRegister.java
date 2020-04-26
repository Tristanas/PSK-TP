package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.TrainersDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class LoginRegister {
    @Inject
    private TrainersDAO trainersDAO;

    @Setter
    @Getter
    private Trainer trainer;

    @Setter
    @Getter
    private String username;

    public String login() {
        trainer = trainersDAO.findByUsername(username);
        // No trainer found, there should be a notification for failed login.
        if (trainer == null) {
            return "index";
        }

        return "trainer?faces-redirect=true&teamId=" + this.trainer.getId();
    }

    @Transactional
    @LoggedInvocation
    public String registerTrainer() {
        trainer = new Trainer();
        trainer.setUsername(username);
        trainer.setLevel(1);
        trainer.setXp(0);
        trainersDAO.persist(trainer);
        return "trainer?faces-redirect=true&teamId=" + this.trainer.getId();
    }
}
