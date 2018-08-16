package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.TimePeriodEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface TimePeriodDao extends Dao<TimePeriodEntity> {

    List<TimePeriodEntity> getCustomTimePeriodList(int fromWeekDay, int toWeekDay) throws DaoException;
}
