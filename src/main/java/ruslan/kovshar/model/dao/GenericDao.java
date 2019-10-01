package ruslan.kovshar.model.dao;

import java.util.List;

/**
 * Generic DAO
 *
 * @param <T> entity
 */
public interface GenericDao<T> extends AutoCloseable {
    /**
     * saves new entity to database
     *
     * @param entity entity
     */
    void create(T entity);

    /**
     * finds in database entity by id
     *
     * @param id entity id
     * @return entity
     */
    T findById(Long id);

    /**
     * finds all entities
     *
     * @return list of all entities
     */
    List<T> findAll();

    /**
     * updates the entity
     *
     * @param entity entity
     */
    void update(T entity);

    /**
     * deletes entity from database
     *
     * @param id entity id
     */
    void delete(Long id);

    /**
     * closes connection
     */
    void close();
}
