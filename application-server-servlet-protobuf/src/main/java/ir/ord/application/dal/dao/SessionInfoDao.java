package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.DevicePushToken;
import ir.ord.application.dal.entities.SessionInfoEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */
public interface SessionInfoDao extends Dao<SessionInfoEntity> {
    List<SessionInfoEntity> get(String deviceId, String accountId) throws DaoException;

    SessionInfoEntity getActiveSessionById(String sessionId) throws DaoException;

    List<SessionInfoEntity> getAllActiveAccountSessions(String id) throws DaoException;

    DevicePushToken getDevicePushToken(String accountId) throws DaoException;

    List<SessionInfoEntity> getActiveSessionsByDeviceId(String deviceId) throws DaoException;

    SessionInfoEntity getBySecondaryId(String secondaryId) throws DaoException;

    List<SessionInfoEntity> getAllActiveSessions() throws DaoException;

}
