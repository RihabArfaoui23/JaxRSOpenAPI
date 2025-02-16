package fr.istic.taa.jaxrs.dao.DomainDAO;

import fr.istic.taa.jaxrs.Domain.Ticket;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class TicketDao extends AbstractJpaDao<Long, Ticket> {

    private final EntityManager entityManager = EntityManagerHelper.getEntityManager();

    @Override
    public Ticket findOne(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    @Override
    public List<Ticket> findAll() {
        return entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class)
                .getResultList();
    }

    @Override
    public void save(Ticket ticket) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Ticket updatedTicket = entityManager.merge(ticket);
            tx.commit();
            return updatedTicket;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Ticket ticket) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(entityManager.contains(ticket) ? ticket : entityManager.merge(ticket));
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
        Ticket ticket = findOne(id);
        if (ticket != null) {
            delete(ticket);
        }
    }
}
