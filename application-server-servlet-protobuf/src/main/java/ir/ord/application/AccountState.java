package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum AccountState {
    NEW_ACCOUNT(1),
    ACTIVE_ACCOUNT(2);

    private final Integer code;

    AccountState(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
