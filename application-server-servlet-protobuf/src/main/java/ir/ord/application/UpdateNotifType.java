package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifType {
    ORDER(1),
    ACCOUNT(2),
    DEVICE(3),
    ADDRESS(4),
    CREDIT(5);

    private final Integer code;

    UpdateNotifType(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
