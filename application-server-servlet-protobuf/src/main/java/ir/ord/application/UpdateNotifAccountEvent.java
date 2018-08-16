package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifAccountEvent {
    QR_CODE_USED(1),
    UPDATE_INFO(2),
    UPDATE_PHONE_NUMBER(3),
    SIGN_OUT(4);

    private final Integer code;

    UpdateNotifAccountEvent(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
