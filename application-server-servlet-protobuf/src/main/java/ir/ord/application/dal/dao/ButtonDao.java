package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ButtonEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface ButtonDao extends Dao<ButtonEntity> {

    ButtonEntity getByDeviceToken(String deviceToken) throws DaoException;

    List<ButtonEntity> getListByAccountId(String accountId) throws DaoException;

    List<ButtonEntity> getListByAccountAndAddressId(String accountId, String addressID) throws DaoException;
}
