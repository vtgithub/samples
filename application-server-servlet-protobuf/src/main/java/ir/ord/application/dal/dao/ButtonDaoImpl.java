package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ButtonEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

public class ButtonDaoImpl extends DaoImpl<ButtonEntity> implements ButtonDao {

    public ButtonEntity getByDeviceToken(String deviceToken) {
        return null;
    }

    public List<ButtonEntity> getListByAccountId(String accountId) throws DaoException {
        return null;
    }

    public List<ButtonEntity> getListByAccountAndAddressId(String accountId, String addressID) throws DaoException {
        return null;
    }
}
