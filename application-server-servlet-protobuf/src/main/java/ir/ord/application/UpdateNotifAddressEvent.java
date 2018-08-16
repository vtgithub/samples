package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifAddressEvent {
    INSERT(1),
    UPDATE(2),
    DELETE(3),
    TIME_PERIOD_UPDATE(4);

    private final Integer code;

    UpdateNotifAddressEvent(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
