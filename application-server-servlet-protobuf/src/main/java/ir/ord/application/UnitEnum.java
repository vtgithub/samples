package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UnitEnum {
    mgr(1),
    gr(2),
    kgr(3),
    percent(4),
    bottle(5),
    cal(6);

    private final Integer code;

    UnitEnum(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
