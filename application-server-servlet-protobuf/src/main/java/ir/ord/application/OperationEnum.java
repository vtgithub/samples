package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum OperationEnum {
    INSERT((byte)1),
    UPDATE((byte)2),
    DELETE((byte)3);

    private final Byte code;

    OperationEnum(Byte i) {
        this.code = i;
    }



    public Byte getCode(){
        return code;
    }
}
