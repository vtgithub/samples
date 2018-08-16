package Accessories;

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