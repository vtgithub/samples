package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ProductEntity;

import java.util.List;

/**
 * Created by vahid on 12/23/17.
 */
public interface ProductDao extends Dao<ProductEntity> {
    List<ProductEntity> getListByCatId(String catId) throws DaoException;
}
