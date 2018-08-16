package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.CountersEntity;

/**
 * Created by vahid on 4/22/17.
 */
public interface CountersDao extends Dao<CountersEntity> {

    Long getNextSequence(String bankOrderSequence);
}
