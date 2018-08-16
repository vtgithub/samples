package ir.ord.management.dal.dao;

import ir.ord.management.RPCEntityEnum;
import ir.ord.management.dal.rpc.CacheService;
import ir.ord.management.dal.rpc.RemoteDaoException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.nio.ByteBuffer;

/**
 * Created by vahid on 7/25/17.
 */
@ApplicationScoped
@Transactional
public class RemoteDaoThriftImpl implements RemoteDao{
    TTransport transport = new TSocket("localhost", 6000);

    public void save(byte[] entity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer byteBufferEntity = ByteBuffer.wrap(entity);
            client.save(byteBufferEntity, rpcEntityEnum);
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }


    public void update(String id, byte[] newEntity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer byteBufferEntity = ByteBuffer.wrap(newEntity);
            client.update(id, byteBufferEntity, rpcEntityEnum);
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }

    }

    public void remove(byte[] entity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {

        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer byteBufferEntity = ByteBuffer.wrap(entity);
            client.remove(byteBufferEntity, rpcEntityEnum);
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }

    }

    public void removeById(String id, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            client.removeById(id, rpcEntityEnum);
            transport.close();
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }

    public byte[] getById(String id, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer result = client.getById(id, rpcEntityEnum);
            transport.close();
            return result.array();
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }

    public byte[] getAll(RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer result = client.getAll(rpcEntityEnum);
            transport.close();
            return result.array();
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }

    public void fillTimePeriodList(byte[] entity, RPCEntityEnum rpcEntityEnum) throws RemoteDaoException {
        try{
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);
            ByteBuffer byteBufferEntity = ByteBuffer.wrap(entity);
            client.fillTimePeriodList(byteBufferEntity, rpcEntityEnum);
            transport.close();
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }finally {
            transport.close();
        }
    }


}
