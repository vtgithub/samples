package ir.ord.application.dal.entities;

/**
 * Created by vahid on 5/8/17.
 */
public class VerifyResponseObject {
    private Integer ResCode;
    private Double Amount;//مبلغ تراکنش
    private String Description;// شرح نتیجه تراکنش
    private String RetrivalRefNo; // شماره مرجع تراکنش
    private String SystemTraceNo;// شسماره پیگیری تراکنش خرید
    private String OrderId; //شماره سفارش

    public Integer getResCode() {
        return ResCode;
    }

    public void setResCode(Integer resCode) {
        ResCode = resCode;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRetrivalRefNo() {
        return RetrivalRefNo;
    }

    public void setRetrivalRefNo(String retrivalRefNo) {
        RetrivalRefNo = retrivalRefNo;
    }

    public String getSystemTraceNo() {
        return SystemTraceNo;
    }

    public void setSystemTraceNo(String systemTraceNo) {
        SystemTraceNo = systemTraceNo;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
}
