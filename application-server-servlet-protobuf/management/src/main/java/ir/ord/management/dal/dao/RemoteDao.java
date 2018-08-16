package ir.ord.management.dal.dao;

import ir.ord.management.RPCEntityEnum;
import ir.ord.management.dal.rpc.RemoteDaoException;
import org.apache.thrift.TException;

/**
 * Created by vahid on 4/22/17.
 */
public interface RemoteDao {
    void save(byte[] entity,RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
    void update(String id, byte[] newEntity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
    void remove(byte[] entity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
    void removeById(String id, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
    byte[] getById(String id, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
    byte[] getAll(RPCEntityEnum rpcEntityEnum) throws  RemoteDaoException;
    void fillTimePeriodList(byte[] entity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException;
}

