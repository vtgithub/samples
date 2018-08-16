package ir.ord.application.dto;

import ir.ord.application.biz_layer.biz.BankInfoDto;

import javax.validation.constraints.NotNull;

/**
 * Created by vahid on 5/2/17.
 */
public class CreditDto {
    private String id;
    private Double amount;
    private Long payDate;
    private String orderId;
    private BankInfoDto bankInfoDto;
    private String description;
    private Double balance;
    private Integer payType;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPayDate() {
        return payDate;
    }

    public void setPayDate(Long payDate) {
        this.payDate = payDate;
    }

    public BankInfoDto getBankInfoDto() {
        return bankInfoDto;
    }

    public void setBankInfoDto(BankInfoDto bankInfoDto) {
        this.bankInfoDto = bankInfoDto;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
