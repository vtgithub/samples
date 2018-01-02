exception RemoteException {
    1:string message;
}

enum TypeEnum{
    TYPE1 = 1,
    TYPE2= 2
}

struct TestStruct{
    1: i64 key,
    2: string value
}
service TestService {
    string proccess(1: list<TestStruct> testStructList);
//    void save(1:binary entity, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    void update(1:string id, 2:binary entity, 3:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    void remove(1:binary entity, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    void removeById(1:string id, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    void fillTimePeriodList(1:binary entity, 2:RPCEntityEnum entityTpe) throws (1:RemoteDaoException daoException),
//    binary getAll(1:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException),
//    binary getById(1:string id, 2:RPCEntityEnum entityType) throws (1:RemoteDaoException daoException)


}