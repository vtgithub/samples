package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.DevicePushToken;
import ir.ord.application.dal.entities.SessionInfoEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */

public class SessionInfoDaoImpl extends DaoImpl<SessionInfoEntity> implements SessionInfoDao {

//    public SessionInfoEntity get(String deviceId, String accountId) throws DaoException {
//        String queryStr = "{$and:[{deviceId:'"+deviceId+"'},{accountId:'"+accountId+"'}]}";
//        try{
//            SessionInfoEntity singleResult = (SessionInfoEntity) em.
//                    createNativeQuery(queryStr, SessionInfoEntity.class).getSingleResult();
//            return singleResult;
//        }catch (NoResultException e){
//            e.printStackTrace();
//            return null;
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new DaoException(e);
//        }
//    }

    public List<SessionInfoEntity> get(String deviceId, String accountId) throws DaoException {
        return null;
    }

    public SessionInfoEntity getActiveSessionById(String sessionId) throws DaoException {
        return null;
    }

    public List<SessionInfoEntity> getAllActiveAccountSessions(String id) throws DaoException {
        return null;
    }

    public DevicePushToken getDevicePushToken(String accountId) throws DaoException {
        return null;
    }


    public List<SessionInfoEntity> getActiveSessionsByDeviceId(String deviceId) throws DaoException {
        return null;
    }

    public SessionInfoEntity getBySecondaryId(String secondaryId) {
        return null;
    }

    public List<SessionInfoEntity> getAllActiveSessions() throws DaoException {
        return null;
    }
}
