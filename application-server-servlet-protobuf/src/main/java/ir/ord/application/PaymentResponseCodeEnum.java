package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum PaymentResponseCodeEnum {
    SUCCESS(0);

    private final Integer code;

    PaymentResponseCodeEnum(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
