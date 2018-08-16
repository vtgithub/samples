package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;

/**
 * Created by vahid on 5/8/17.
 */
public class PaymentRequestObject {

    private String MerchantId = DaoHelper.getMercahntId();
    private String TerminalId = DaoHelper.getTerminalId();
    private Long Amount;
    private Long OrderId ;
    private String LocalDateTime = DaoHelper.getJsonDate();
    private String ReturnUrl =  DaoHelper.getBankReturnUrl();
    private String SignData;
    private String AdditionalData;
    private String MultiplexingData;

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String terminalId) {
        TerminalId = terminalId;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public String getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        LocalDateTime = localDateTime;
    }

    public String getReturnUrl() {
        return ReturnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        ReturnUrl = returnUrl;
    }

    public String getSignData() {
        return SignData;
    }

    public void setSignData(String signData) {
        SignData = signData;
    }

    public String getAdditionalData() {
        return AdditionalData;
    }

    public void setAdditionalData(String additionalData) {
        AdditionalData = additionalData;
    }

    public String getMultiplexingData() {
        return MultiplexingData;
    }

    public void setMultiplexingData(String multiplexingData) {
        MultiplexingData = multiplexingData;
    }
}
