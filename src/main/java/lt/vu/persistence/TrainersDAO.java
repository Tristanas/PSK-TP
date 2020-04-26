package lt.vu.persistence;

import lt.vu.entities.Trainer;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TrainersDAO {

    @Inject
    private EntityManager em;

    public void persist(Trainer trainer){
        this.em.persist(trainer);
    }

    public Trainer findOne(Integer id){
        return em.find(Trainer.class, id);
    }

    public Trainer findByUsername(String username) {
        return em.createNamedQuery("Trainer.findByUsername", Trainer.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Trainer update(Trainer trainer){
        return em.merge(trainer);
    }
}
