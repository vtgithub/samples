package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.OrderEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

public class OrderDaoImpl extends DaoImpl<OrderEntity> implements OrderDao {

    public List<OrderEntity> getListByAccountId(String accountId) throws DaoException {
        return null;
    }

    public long getOrderCountByAccountId(String accountId) throws DaoException {
        return 0;
    }

    public List<OrderEntity> getExpiredPaiedOrders() throws DaoException {
        return null;
    }

    public List<OrderEntity> getWarehousePendingList() throws DaoException {
        return null;
    }
}
