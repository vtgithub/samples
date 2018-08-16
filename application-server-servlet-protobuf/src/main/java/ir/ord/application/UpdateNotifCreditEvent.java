package ir.ord.application;

/**
 * Created by vahid on 4/26/17.
 */
public enum UpdateNotifCreditEvent {
    ORDER(1),
    ORDER_CANCELATION(2),
    CHARGE(3),
    CHARGE_GIFT(4),
    DELIVERY(5);

    private final Integer code;

    UpdateNotifCreditEvent(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
