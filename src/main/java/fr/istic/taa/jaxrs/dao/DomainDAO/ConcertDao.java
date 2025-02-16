package fr.istic.taa.jaxrs.dao.DomainDAO;

import fr.istic.taa.jaxrs.Domain.Concert;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class ConcertDao extends AbstractJpaDao<Long, Concert> {

    private final EntityManager entityManager = EntityManagerHelper.getEntityManager();

    @Override
    public Concert findOne(Long id) {
        return entityManager.find(Concert.class, id);
    }

    @Override
    public List<Concert> findAll() {
        return entityManager.createQuery("SELECT c FROM Concert c", Concert.class)
                .getResultList();
    }

    @Override
    public void save(Concert concert) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(concert);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Concert update(Concert concert) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Concert updatedConcert = entityManager.merge(concert);
            tx.commit();
            return updatedConcert;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Concert concert) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(entityManager.contains(concert) ? concert : entityManager.merge(concert));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        Concert concert = findOne(id);
        if (concert != null) {
            delete(concert);
        }
    }
}
