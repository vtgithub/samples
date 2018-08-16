package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.PhoneLogEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class PhoneLogDaoMongoImpl extends DaoMongoImpl<PhoneLogEntity> implements PhoneLogDao{

}
