package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifDeviceEvent {
    X(1);
    private final Integer code;

    UpdateNotifDeviceEvent(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
