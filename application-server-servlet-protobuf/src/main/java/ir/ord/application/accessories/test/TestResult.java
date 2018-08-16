package ir.ord.application.accessories.test;

/**
 * Created by vahid on 8/30/17.
 */
public class TestResult {
    private boolean protoResult;
    private boolean jsonResult;

    public boolean isProtoResult() {
        return protoResult;
    }

    public void setProtoResult(boolean protoResult) {
        this.protoResult = protoResult;
    }

    public boolean isJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(boolean jsonResult) {
        this.jsonResult = jsonResult;
    }

    @Override
    public String toString() {
        return "{" +
                "protoResult=" + protoResult +
                ", jsonResult=" + jsonResult +
                '}';
    }
}
