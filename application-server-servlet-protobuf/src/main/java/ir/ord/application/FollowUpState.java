package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum FollowUpState {
    FOLLOW_SMS_SENT(1),
    CANCEL_SMS_SENT(2),
    INTERNAL_PROBLEM(-10),
    INVALID_ORDER_ID(-11),
    IT_IS_OK(0), FOLLOW_SMS_SENDING_FAILED(-1), CANCEL_SMS_SENDING_FAILED(-2);

    private final Integer code;

    FollowUpState(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
