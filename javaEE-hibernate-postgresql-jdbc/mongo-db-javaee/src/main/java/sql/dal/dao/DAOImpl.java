package sql.dal.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 3/24/17.
 */
public abstract class DAOImpl<E> implements DAO<E>{


    @PersistenceContext(unitName = "mongo")
    EntityManager entityManager;

    public void add(E entity){
        entityManager.persist(entity);
    }

    public void update(E entity){
        entityManager.flush();
        entityManager.merge(entity);
    }

    public void remove(E entity){
        entityManager.remove(entity);
    }

    public E getById(Long id){
        String queryStr = String.format("SELECT table from %s table WHERE table.id = :id",
                DBHelper.getEntityClassName(this.getClass().getSuperclass())
        );
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("id", id);
        E result = (E) query.getSingleResult();
        return result;
    }

    public List<E> getAll(){

        String queryStr = String.format("SELECT table from %s table" ,
                DBHelper.getEntityClassName(this.getClass())
        );
        Query query = entityManager.createQuery(queryStr);
        List<E> resultList = query.getResultList();
        return resultList;
//        return null;
    }
}
