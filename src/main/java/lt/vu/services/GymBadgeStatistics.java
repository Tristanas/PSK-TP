package lt.vu.services;

import lt.vu.entities.GymBadge;
import lt.vu.persistence.GymBadgeDAO;

import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class GymBadgeStatistics implements Serializable {

    @Inject
    private GymBadgeDAO badgeDAO;

//    If this method is called asynchronously and uses badgeDAO, ExecutionExceptions occur and everything fails.
    public Integer getBadgeEarnedCount(Integer badgeId) {
//        GymBadge badge = badgeDAO.findOne(badgeId);
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println("REEEEEException");
        }
//        return badgeDAO.getEarnedGymBadges(badge).size();
        return 2;
    }
}
