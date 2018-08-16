package ir.ord.application.dal.entities;



import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.NotNull;

/**
 * Created by vahid on 4/26/17.
 */
//@Entity
//@Table
public class CreditEntity {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
    @NotNull
    private Double amount;
    @NotNull
    private Long payDate;
    @NotNull
    private String accountId;
//    @Embedded
    private String orderId;

    private BankInfoObject bankInfoObject;

    private String description;
    @NotNull
    private Double balance;
//    @NotNull
    private Integer payType;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BankInfoObject getBankInfoObject() {
        return bankInfoObject;
    }

    public void setBankInfoObject(BankInfoObject bankInfoObject) {
        this.bankInfoObject = bankInfoObject;
    }

    public Long getPayDate() {
        return payDate;
    }

    public void setPayDate(Long payDate) {
        this.payDate = payDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
