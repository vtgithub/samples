package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum ButtonState {
    BOUGHT(1),
    ACTIVATED(2);

    private final Integer code;

    ButtonState(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
