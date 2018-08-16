package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.AddressObject;

import javax.persistence.NoResultException;

/**
 * Created by vahid on 4/24/17.
 */

public class AccountInfoDaoImpl extends DaoImpl<AccountInfoEntity> implements AccountInfoDao{

    public AccountInfoEntity getByPhoneNumber(String phoneNumber) throws DaoException {
        String queryStr = "{phoneNumber:'"+phoneNumber+"'}";
        try{
            AccountInfoEntity accountInfoEntity = (AccountInfoEntity) em.
                    createNativeQuery(queryStr, AccountInfoEntity.class).getSingleResult();
            return accountInfoEntity;
        }catch (NoResultException e){
            
            return null;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public AddressObject getAddress(String accountId, String addressId) throws DaoException {
        return null;
    }

    public AddressObject getAddressByEntity(AccountInfoEntity accountInfoEntity, String addressId) {
        return null;
    }
}
