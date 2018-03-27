package com.jfms.authentication.authentication.api.model;

public class UserRegistrationRequest {
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private Integer activationCodeLength;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getActivationCodeLength() {
        return activationCodeLength;
    }

    public void setActivationCodeLength(Integer activationCodeLength) {
        this.activationCodeLength = activationCodeLength;
    }
}
