package lt.vu.persistence;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.GymBadge;
import lt.vu.entities.IXpSource;
import lt.vu.entities.Trainer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@ApplicationScoped
public class TrainersDAO implements IXpSource {

    @Inject
    private EntityManager em;

    @Setter
    @Getter
    private Integer currentTrainerId;

    public void persist(Trainer trainer){
        this.em.persist(trainer);
    }

    public Trainer findOne(Integer id){
        return em.find(Trainer.class, id);
    }

    public Trainer findByUsername(String username) throws NoResultException {
        return em.createNamedQuery("Trainer.findByUsername", Trainer.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public boolean hasGymBadge(GymBadge badge, Trainer trainer) {
        return trainer.getGymBadges().contains(badge);
    }

    public void addGymBadge(GymBadge badge, Trainer trainer) {
        if (! hasGymBadge(badge, trainer)) {
            trainer.getGymBadges().add(badge);
            trainer.gainXP(200);
        }
    }

    public Trainer getCurrentTrainer() {
        return findOne(currentTrainerId);
    }

    public Trainer update(Trainer trainer){
        return em.merge(trainer);
    }

    public void giveXP(int xp, Trainer trainer) {
        trainer.gainXP(xp);
        update(trainer);
    }
}
