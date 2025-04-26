package fr.istic.taa.jaxrs.dao.DomainDAO;

import fr.istic.taa.jaxrs.Domain.Utilisateur;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class UtilisateurDao extends AbstractJpaDao<Long, Utilisateur> {

    private final EntityManager entityManager = EntityManagerHelper.getEntityManager();

    @Override
    public Utilisateur findOne(Long id) {
        return entityManager.find(Utilisateur.class, id);
    }

    @Override
    public List<Utilisateur> findAll() {
        return entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                .getResultList();
    }

    @Override
    public void save(Utilisateur user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Utilisateur update(Utilisateur user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Utilisateur updatedUser = entityManager.merge(user);
            tx.commit();
            return updatedUser;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Utilisateur user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
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
        Utilisateur user = findOne(id);
        if (user != null) {
            delete(user);
        }
    }

    public Utilisateur findByEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // or handle NotFound more gracefully
        }
    }
}
