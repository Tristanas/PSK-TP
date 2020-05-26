package lt.vu.persistence;

import lt.vu.entities.GymBadge;
import lt.vu.entities.Trainer;
import lt.vu.qualifiers.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TransactionalGymBadgeDAO {

    @Inject
    @Transaction
    private EntityManager em;

    public GymBadge findOne(Integer id) { return em.find(GymBadge.class, id); }

    public int getEarnedCount(GymBadge badge) {
        if (badge == null)
            return 0;

        List<Trainer> trainers = em.createNamedQuery("Trainer.findAll", Trainer.class)
                .getResultList();

        int count = 0;

        for (Trainer trainer: trainers
        ) {
            if (trainer.getGymBadges().contains(badge))
                count++;
        }

        return count;
    }
}