package ir.ord.application.dal.entities;

/**
 * Created by vahid on 9/5/17.
 */
public class BankInfoObject {
    private String token;
    private Integer bankId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
}
