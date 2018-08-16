package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ActivationEntity;

/**
 * Created by vahid on 4/22/17.
 */
public interface ActivationDao extends Dao<ActivationEntity> {

    ActivationEntity get(String activationCode, String deviceId, Boolean isUsed) throws DaoException;

    Long getDailyUnusedActivationCodeCount(String deviceId) throws DaoException;

}
