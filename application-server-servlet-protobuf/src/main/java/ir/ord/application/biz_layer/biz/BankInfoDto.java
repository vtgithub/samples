package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 9/5/17.
 */
public class BankInfoDto {
    private String token;
    private Integer bankId;

    public BankInfoDto() {
    }

    public BankInfoDto(String token, Integer bankId) {
        this.token = token;
        this.bankId = bankId;
    }

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
