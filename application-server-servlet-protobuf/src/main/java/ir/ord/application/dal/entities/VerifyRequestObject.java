package ir.ord.application.dal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vahid on 5/8/17.
 */
public class VerifyRequestObject {
    private String Token;
    private String SignData;

    @JsonProperty("Token")
    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @JsonProperty("SignData")
    public String getSignData() {
        return SignData;
    }

    public void setSignData(String signData) {
        SignData = signData;
    }
}
