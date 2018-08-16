package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum NotifType {
    NOTIFICATION((byte)1),
    CONTENT_UPDATE((byte)2),
    SMS((byte)3);

    private final Byte code;

    NotifType(Byte i) {
        this.code = i;
    }



    public Byte getCode(){
        return code;
    }
}
