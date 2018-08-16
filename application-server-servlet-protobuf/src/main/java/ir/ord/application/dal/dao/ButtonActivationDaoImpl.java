package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.ActivationEntity;
import ir.ord.application.dal.entities.ButtonActivationEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */

public class ButtonActivationDaoImpl extends DaoImpl<ButtonActivationEntity> implements ButtonActivationDao {

    public ButtonActivationEntity get(String deviceToken, String activationCode, Boolean isUsed) {
        return null;
    }

}
