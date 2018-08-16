package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.MessageEntity;

import java.util.List;

/**
 * Created by vahid on 11/26/17.
 */
public interface MessageDao extends Dao<MessageEntity>{

    List<MessageEntity> getCurrentMessage(Long currentTime) throws DaoException;
}
