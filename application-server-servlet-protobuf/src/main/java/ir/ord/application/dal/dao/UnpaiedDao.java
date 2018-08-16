package ir.ord.application.dal.dao;


import ir.ord.application.dal.entities.UnpaiedEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface UnpaiedDao extends Dao<UnpaiedEntity> {

    List<UnpaiedEntity> getByAccountId(String accountId);
    void removeByOrderId(String orderId) throws DaoException;
    UnpaiedEntity getByOrderId(String orderId) throws DaoException;
}
