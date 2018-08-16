package ir.ord.application.dal.entities;

/**
 * Created by vahid on 5/8/17.
 */
public class SuccessPaymentObject {
    private String Token;
    private Long OrderId;
    private Integer ResCode;  // 0 success , -1 unsuccess

    public SuccessPaymentObject(String token, Long orderId, Integer resCode) {
        Token = token;
        OrderId = orderId;
        ResCode = resCode;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Integer getResCode() {
        return ResCode;
    }

    public void setResCode(Integer resCode) {
        ResCode = resCode;
    }
}
