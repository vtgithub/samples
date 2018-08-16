package ir.ord.application.accessories.test;

/**
 * Created by vahid on 10/2/17.
 */
public class TestBodyResponse {
    private Integer responseCode;
    private String message;
    byte[] data;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
