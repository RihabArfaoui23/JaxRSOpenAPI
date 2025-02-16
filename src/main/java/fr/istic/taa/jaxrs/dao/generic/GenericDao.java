package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Interface générique pour les opérations CRUD.
 *
 * @param <T>  Type de l'entité
 * @param <K> Type de la clé primaire (doit être sérialisable)
 */
public interface GenericDao<K, T extends Serializable> {

    /**
     * Trouve une entité par son ID.
     *
     * @param id L'ID de l'entité.
     * @return L'entité correspondante.
     */
    T findOne(final K id);

    /**
     * Récupère toutes les entités.
     *
     * @return Liste des entités.
     */
    List<T> findAll();

    /**
     * Sauvegarde une entité dans la base de données.
     *
     * @param entity L'entité à sauvegarder.
     */
    void save(final T entity);

    /**
     * Met à jour une entité existante.
     *
     * @param entity L'entité à mettre à jour.
     * @return L'entité mise à jour.
     */
    T update(final T entity);

    /**
     * Supprime une entité.
     *
     * @param entity L'entité à supprimer.
     */
    void delete(final T entity);

    /**
     * Supprime une entité par son ID.
     *
     * @param entityId L'ID de l'entité à supprimer.
     */
    void deleteById(final K entityId);
}
