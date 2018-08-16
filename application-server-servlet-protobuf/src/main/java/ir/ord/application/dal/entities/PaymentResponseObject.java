package ir.ord.application.dal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vahid on 5/8/17.
 */
public class PaymentResponseObject {
    private String ResCode;
    private String Token;
    private String Description;

    @JsonProperty("ResCode")
    public String getResCode() {
        return ResCode;
    }

    public void setResCode(String ResCode) {
        this.ResCode = ResCode;
    }
    @JsonProperty("Token")
    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
