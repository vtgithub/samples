package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.CreditEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

public class CreditDaoImpl extends DaoImpl<CreditEntity> implements CreditDao {

    public List<CreditEntity> getListByAccountId(String accountId) {
                return null;
    }

    public CreditEntity getByAccountIdAndBankToken(String accountId, String bankToken) throws DaoException {
        return null;
    }

    public CreditEntity getByAccountIdAndOrderId(String accountId, String orderId) throws DaoException {
        return null;
    }

    public CreditEntity getByAccountIdAndInfo(String accountId, String info) throws DaoException {
        return null;
    }

    public Double getBalanceSum(String accountId) throws DaoException {
        return null;
    }
}
