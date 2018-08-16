package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;

/**
 * Created by vahid on 5/20/17.
 */
public class PhoneLogEntity {
    private String _id = DaoHelper.getUUID();
    private String accountId;
    private Long activationDate;
    private String phoneNumber;
    private Integer reason;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Long activationDate) {
        this.activationDate = activationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }
}
