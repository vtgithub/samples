package ir.ord.application.accessories;

/**
 * Created by vahid on 7/31/17.
 */
public class RequestResponse {
    private Integer responseCode;
    private Object responseBody;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
