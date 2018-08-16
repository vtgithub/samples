package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum ActorEnum {
    USER(1),
    SYSTEM(2),
    WAREHOUSE(3),
    LOGISTIC(4),
    BUTTON(5);

    private final Integer code;

    ActorEnum(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
