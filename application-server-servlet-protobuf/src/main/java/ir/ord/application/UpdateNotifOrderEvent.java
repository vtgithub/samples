package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifOrderEvent {
    SUCCESS_ORDER(1),
    STATUS_UPDATE(2);

    private final Integer code;

    UpdateNotifOrderEvent(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
