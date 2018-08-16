package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.GiftEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface GiftDao extends Dao<GiftEntity> {
    GiftEntity getUnused(String code) throws DaoException;

    GiftEntity getByCode(String giftChargeCode) throws DaoException;

    List<GiftEntity> getByAccountId(String accountId) throws DaoException;
}
