package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum PlatformEnum {
    ANDROID(2),
    IOS(1);
    private final Integer code;

    PlatformEnum(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
