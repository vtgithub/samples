package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum BankEnum {
    SADAD(1);

    private final Integer code;

    BankEnum(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
