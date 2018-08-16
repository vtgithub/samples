package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ButtonActivationEntity;

/**
 * Created by vahid on 4/22/17.
 */
public interface ButtonActivationDao extends Dao<ButtonActivationEntity> {
    ButtonActivationEntity get(String deviceToken, String activationCode, Boolean isUsed) throws DaoException;
}
