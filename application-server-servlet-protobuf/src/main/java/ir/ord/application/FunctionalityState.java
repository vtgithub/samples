package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum FunctionalityState {
    FUNC_KEY_GENERATED(1);

    private final Integer code;

    FunctionalityState(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
