exception RemoteDaoException {
    1:string message;
}

struct OrderStruct{
    1: string id,
    2: string packageName,
    3: string packageId
}

service WarehouseService{
    list<OrderStruct> getWarehousePendingOrderList() throws (1:RemoteDaoException remoteDaoException);
    void changeOrderStatusToWarehouseProccessing(1:string orderId) throws (1:RemoteDaoException remoteDaoException);
}