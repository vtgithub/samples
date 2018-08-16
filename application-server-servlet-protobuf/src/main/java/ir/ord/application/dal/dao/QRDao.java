package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.QREntity;

/**
 * Created by vahid on 4/22/17.
 */
public interface QRDao extends Dao<QREntity> {

    QREntity getBySessionId(String sessionId, Boolean b, Long creationTime) throws DaoException;

    QREntity getByEncryptedCode(String base64Encrypted, Boolean used, Long creationTime) throws DaoException;
}
