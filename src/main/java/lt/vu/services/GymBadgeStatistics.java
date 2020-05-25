package lt.vu.services;

import lt.vu.entities.GymBadge;
import lt.vu.persistence.TransactionalGymBadgeDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@ApplicationScoped
public class GymBadgeStatistics implements Serializable {
    // Special version of badgeDAO which uses TransactionScoped entity manager.
    @Inject
    private TransactionalGymBadgeDAO badgeDAO;

//    If this method is called asynchronously and uses badgeDAO, ExecutionExceptions occur and everything fails.
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Integer getBadgeEarnedCount(Integer badgeId) {
        int count = 0;
        try {
            GymBadge badge = badgeDAO.findOne(badgeId);
            count = badgeDAO.getEarnedCount(badge);
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println("REEEEEException");
        }
        return count;
    }
}
