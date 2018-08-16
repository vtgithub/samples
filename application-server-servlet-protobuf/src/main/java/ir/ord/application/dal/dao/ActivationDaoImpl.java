package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ActivationEntity;

import javax.persistence.NoResultException;

/**
 * Created by vahid on 4/22/17.
 */

public class ActivationDaoImpl extends DaoImpl<ActivationEntity> implements ActivationDao {

    public ActivationEntity get(String activationCode, String deviceId, Boolean isUsed) throws DaoException {

        String queryStr = "{$and:[{deviceId : '"+deviceId+"'}," +
                "{activationCode : '"+activationCode+"'}," +
                "{isUsed : "+isUsed+"}]}";
        try{
            ActivationEntity activationEntity = (ActivationEntity) em.
                    createNativeQuery(queryStr, ActivationEntity.class).getSingleResult();
            return activationEntity;
        }catch (NoResultException e){
         
            return null;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public Long getDailyUnusedActivationCodeCount(String deviceId) {
        return null;
    }

    public boolean isInBlackList(String phoneNumber, String deviceId) {
        return false;
    }
}
