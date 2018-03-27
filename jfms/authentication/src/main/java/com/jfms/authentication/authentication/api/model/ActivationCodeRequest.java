package com.jfms.authentication.authentication.api.model;

public class ActivationCodeRequest {
    private String mobileNumber;
    private Integer activationCodeLength;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getActivationCodeLength() {
        return activationCodeLength;
    }

    public void setActivationCodeLength(Integer activationCodeLength) {
        this.activationCodeLength = activationCodeLength;
    }
}
