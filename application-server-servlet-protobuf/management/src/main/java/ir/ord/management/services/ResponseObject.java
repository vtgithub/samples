package ir.ord.management.services;

/**
 * Created by vahid on 5/9/17.
 */
public class ResponseObject {
    private Integer responseCode;
    private String message;
    private Object data;
    private Object error;

    public ResponseObject() {
    }

    public ResponseObject(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ResponseObject(Integer responseCode, String message, Object data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(Integer responseCode, String message, Object data, Object error) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
        this.error = error;
    }


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
