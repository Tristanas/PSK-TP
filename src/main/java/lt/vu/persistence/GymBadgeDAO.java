package lt.vu.persistence;

import lt.vu.entities.GymBadge;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GymBadgeDAO {
    @Inject
    private EntityManager em;

    public void persist(GymBadge badge) { em.persist(badge); }

    public GymBadge findOne(Integer id) { return em.find(GymBadge.class, id); }

    public GymBadge update(GymBadge badge){
        return em.merge(badge);
    }

    public List<GymBadge> list() {
        return em.createNamedQuery("GymBadge.findAll", GymBadge.class)
                .getResultList();
    }

}
