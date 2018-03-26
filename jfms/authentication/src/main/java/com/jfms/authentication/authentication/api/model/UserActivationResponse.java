package com.jfms.authentication.authentication.api.model;

public class UserActivationResponse {

    private String token;

    public UserActivationResponse() {
    }
    public UserActivationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
