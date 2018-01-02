package server;

import org.apache.thrift.TException;
import thrift_gen.TestService;
import thrift_gen.TestStruct;

import java.util.List;

/**
 * Created by vahid on 1/2/18.
 */
public class TestServiceHandler implements TestService.Iface {
    public String proccess(List<TestStruct> testStructList) throws TException {

        StringBuilder stringBuilder = new StringBuilder();
        for (TestStruct testStruct : testStructList) {
            stringBuilder.append(testStruct.toString());
        }
        System.out.println("____________" + stringBuilder.toString() + "___________");
        return stringBuilder.toString();
    }
}
