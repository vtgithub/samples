package ir.ord.application.dal.dao;

import ir.ord.application.accessories.Helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

abstract class DaoImpl<E> implements Dao<E> {

    @PersistenceContext(name = "application-server-ogm")
    protected EntityManager em;
    public void save(E entity) throws DaoException {
        try {
            em.persist(entity);
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public void update(String id, E newEntity) throws DaoException {
        try{
            em.flush();
            em.merge(newEntity);
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public void remove(E entity) throws DaoException {
        try{
            em.remove(em.contains(entity)?entity:em.merge(entity));
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public void removeById(String id) throws DaoException {
        //TODO remove code
    }

    public List<E> getAll() throws DaoException {
        try{
            List<E> resultList = em.createNativeQuery("{}",
                    Helper.getEntityClass(this.getClass().getSuperclass())).getResultList();
            return resultList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public List<E> getAllForCache() throws DaoException {
        try{
            List<E> resultList = em.createNativeQuery("{}",
                    Helper.getEntityClass(this.getClass().getSuperclass())).getResultList();
            return resultList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public E getById(String id) throws DaoException {
        try {
            System.out.println(">>>>>>>>>>>>>"+Helper.getEntityClass(this.getClass().getSuperclass()));
            String queryStr =  "{ _id:'"+id+"'}";
            E result = (E) em.createNativeQuery(queryStr,
                    Helper.getEntityClass(this.getClass().getSuperclass())).getSingleResult();
            return result;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public void dropEntity() throws DaoException {

    }


}
