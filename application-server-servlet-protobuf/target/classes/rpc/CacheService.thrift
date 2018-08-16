
exception RemoteDaoException {
    1:string message;
}

enum RPCEntityEnum{
    PACKAGE_ENTITY = 1,
    CATEGORY_ENTITY = 2,
    TIME_PERIOD_ENTITY = 3,
    GIFT_ENTITY = 4
}

service CacheService {
    void save(1:binary entity, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
    void update(1:string id, 2:binary entity, 3:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
    void remove(1:binary entity, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
    void removeById(1:string id, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
    void fillTimePeriodList(1:binary entity, 2:RPCEntityEnum entityTpe) throws (1:RemoteDaoException daoException),
    binary getAll(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
    binary getById(1:string id, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException)
//    list<binary> getAll(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    binary getById(1:string id, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),

//    list<binary> getAllButtonPackages(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    list<binary> getPackageListByParentCatId(1:string catId, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),

//    binary getNonButtonCat(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    binary getButtonCat(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    binary findNonButtonById(1:string catId, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),

}