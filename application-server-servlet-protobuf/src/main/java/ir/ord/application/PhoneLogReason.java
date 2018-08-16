package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum PhoneLogReason {
    SIGNUP(1),
    SIGNIN(2),
    CHANGE_NUMBER(3);

    private final Integer code;

    PhoneLogReason(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
