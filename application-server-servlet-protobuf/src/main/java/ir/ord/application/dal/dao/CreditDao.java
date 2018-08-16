package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.CreditEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface CreditDao extends Dao<CreditEntity> {
    List<CreditEntity> getListByAccountId(String accountId) throws DaoException;
    CreditEntity getByAccountIdAndBankToken(String accountId, String bankToken) throws DaoException;
    CreditEntity getByAccountIdAndOrderId(String accountId, String orderId) throws DaoException;
    Double getBalanceSum(String accountId) throws DaoException;
}
