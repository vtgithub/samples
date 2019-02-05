package test.icon.graphql.proxy.proxytest.dto;

import java.util.List;
import java.util.Map;

public class GraphqlRequest {
    private List<String> reqList;

    public GraphqlRequest(List<String> reqList) {
        this.reqList = reqList;
    }

    public GraphqlRequest() {
    }

    public List<String> getReqList() {
        return reqList;
    }

    public void setReqList(List<String> reqList) {
        this.reqList = reqList;
    }
}
