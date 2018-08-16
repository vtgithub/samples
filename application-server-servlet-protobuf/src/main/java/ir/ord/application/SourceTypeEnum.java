package ir.ord.application;

/**
 * Created by vahid on 4/26/17.
 */
public enum SourceTypeEnum {
    WEB((byte)1),
    APP((byte)2),
    BUTTON((byte)3);

    private final Byte code;

    SourceTypeEnum(Byte code) {
        this.code = code;
    }

    public Byte getCode() {
        return code;
    }
}
