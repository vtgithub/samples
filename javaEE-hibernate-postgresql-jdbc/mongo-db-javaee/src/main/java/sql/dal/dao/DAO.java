package sql.dal.dao;

import java.util.List;

/**
 * Created by vahid on 3/24/17.
 */
public interface DAO<E> {
    void add(E entity);
    void update(E entity);
    void remove(E entity);
    E getById(Long id);
    List<E> getAll();
}
