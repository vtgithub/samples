package ir.ord.application.biz_layer.rpc;

import ir.ord.application.RPCEntityEnum;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.entities.CategoryEntity;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by vahid on 7/23/17.
 */
public class RpcClientTest {
    public static void main(String[] args) {

        try {
            TTransport transport = new TSocket("localhost", 6000);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            CacheService.Client client = new CacheService.Client(protocol);

//            TestRpcEntity testRpcEntity = new TestRpcEntity();
//            testRpcEntity.setId(12);
//            testRpcEntity.setValue("vallllll");
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName("ddd");

//            ByteBuffer byteBuffer = ByteBuffer.wrap(Helper.BytesUtil.toByteArray(testRpcEntity));
            ByteBuffer byteBuffer = ByteBuffer.wrap(Helper.BytesUtil.toByteArray(categoryEntity));
            client.save(byteBuffer, RPCEntityEnum.CATEGORY_ENTITY);

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
