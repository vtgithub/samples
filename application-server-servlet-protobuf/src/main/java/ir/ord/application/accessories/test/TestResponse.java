package ir.ord.application.accessories.test;

import java.util.Arrays;

/**
 * Created by vahid on 8/30/17.
 */
public class TestResponse {
    private byte[] responseBytes;
    private int responseCode;

    public byte[] getResponseBytes() {
        return responseBytes;
    }

    public void setResponseBytes(byte[] responseBytes) {
        this.responseBytes = responseBytes;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return  "{ responseBytes=" +new String(responseBytes) +
                ", responseCode=" + responseCode +
                '}';
    }
}
