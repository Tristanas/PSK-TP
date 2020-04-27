package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Trainer;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.TrainersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.NoResultException;
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

    @Setter
    @Getter
    private String error;

    @PostConstruct
    public void init()
    {
        error = "";
    }

    public String login() {
        try{
            trainer = trainersDAO.findByUsername(username);
        } catch (NoResultException e)
        {
            error = "Incorrect login credentials.";
            return "index";
        }

        trainersDAO.setCurrentTrainerId(trainer.getId());
        return "trainer?faces-redirect=true";
    }

    @Transactional
    @LoggedInvocation
    public String registerTrainer() {
        trainer = new Trainer();
        trainer.setUsername(username);
        trainer.setLevel(1);
        trainer.setXp(0);
        trainersDAO.persist(trainer);
        trainersDAO.setCurrentTrainerId(trainer.getId());
        return "trainer?faces-redirect=true";
    }
}
