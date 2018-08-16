package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.AddressObject;

/**
 * Created by vahid on 4/24/17.
 */
public interface AccountInfoDao extends Dao<AccountInfoEntity> {
    AccountInfoEntity getByPhoneNumber(String phoneNumber) throws DaoException;

    AddressObject getAddress(String accountId, String addressId) throws DaoException;

    AddressObject getAddressByEntity(AccountInfoEntity accountInfoEntity, String addressId);
}
