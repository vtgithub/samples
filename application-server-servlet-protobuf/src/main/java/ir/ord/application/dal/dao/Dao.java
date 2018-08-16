package ir.ord.application.dal.dao;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface Dao<E> {
    void save(E entity) throws DaoException;
    void update(String id, E newEntity) throws DaoException;
    void remove(E entity) throws DaoException;
    void removeById(String id) throws DaoException;
    List<E> getAll() throws DaoException;
    List<E> getAllForCache() throws DaoException;
    E getById(String id) throws DaoException;
    void dropEntity() throws DaoException;
}
