package client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift_gen.RemoteException;
import thrift_gen.TestService;
import thrift_gen.TestStruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 1/2/18.
 */
public class Client {
    static  TTransport transport = new TSocket("localhost", 3233);
    public static void main(String[] args) throws RemoteException {
        try{
            transport.open();
            TProtocol tProtocol = new TBinaryProtocol(transport);
            TestService.Client client = new TestService.Client(tProtocol);
//        ByteBuffer byteBufferEntity = ByteBuffer.wrap(entity);

            List<TestStruct> testStructList = new ArrayList<TestStruct>();
            TestStruct testStruct = new TestStruct();
            testStruct.setKey(1000);
            testStruct.setValue("testValue");
            testStructList.add(testStruct);

            String result = client.proccess(testStructList);
            System.out.println(result);
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }finally {
            transport.close();
        }
    }
}
