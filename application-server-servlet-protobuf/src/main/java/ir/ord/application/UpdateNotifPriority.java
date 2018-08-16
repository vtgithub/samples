package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum UpdateNotifPriority {
    HIGHT(10);

    private final Integer code;

    UpdateNotifPriority(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
