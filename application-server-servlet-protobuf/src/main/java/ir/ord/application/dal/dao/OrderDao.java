package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.OrderEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface OrderDao extends Dao<OrderEntity> {
    List<OrderEntity> getListByAccountId(String accountId) throws DaoException;

    long getOrderCountByAccountId(String accountId) throws DaoException;

    List<OrderEntity> getExpiredPaiedOrders() throws DaoException;

    List<OrderEntity> getWarehousePendingList() throws DaoException;
}
