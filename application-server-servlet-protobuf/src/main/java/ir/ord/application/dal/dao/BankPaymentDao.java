package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.BankPaymentEntity;

/**
 * Created by vahid on 4/22/17.
 */
public interface BankPaymentDao extends Dao<BankPaymentEntity> {

    BankPaymentEntity getByOrderId(Long orderId) throws DaoException, DaoException;
}
