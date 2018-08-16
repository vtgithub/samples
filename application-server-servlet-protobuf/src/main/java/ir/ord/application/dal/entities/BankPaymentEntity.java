package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;

/**
 * Created by vahid on 5/8/17.
 */
public class BankPaymentEntity {
    private String _id = DaoHelper.getUUID();
    private String specificOrderId;
    private String sessionId;
    private Long timeStamp;
    private Long amount;
    private Long orderId;
    private SuccessPaymentObject successPaymentObject;
    private VerifyResponseObject verifyResponseObject;
    private Integer bankId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSpecificOrderId() {
        return specificOrderId;
    }

    public void setSpecificOrderId(String specificOrderId) {
        this.specificOrderId = specificOrderId;
    }

    public SuccessPaymentObject getSuccessPaymentObject() {
        return successPaymentObject;
    }

    public void setSuccessPaymentObject(SuccessPaymentObject successPaymentObject) {
        this.successPaymentObject = successPaymentObject;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public VerifyResponseObject getVerifyResponseObject() {
        return verifyResponseObject;
    }

    public void setVerifyResponseObject(VerifyResponseObject verifyResponseObject) {
        this.verifyResponseObject = verifyResponseObject;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
