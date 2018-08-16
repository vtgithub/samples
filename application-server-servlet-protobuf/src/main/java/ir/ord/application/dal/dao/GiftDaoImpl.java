package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.GiftEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

public class GiftDaoImpl extends DaoImpl<GiftEntity> implements GiftDao {


    public GiftEntity getUnused(String code) throws DaoException {
        return null;
    }

    public GiftEntity getByCode(String giftChargeCode) {
        return null;
    }

    public List<GiftEntity> getByAccountId(String accountId) throws DaoException {
        return null;
    }
}
