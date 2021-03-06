package ir.ord.management.accessories;

/**
 * Created by vahid on 4/9/17.
 */
public enum ResponseStatus {

    OK(200),
    CREATED(201),
    ALREADY_REPORTED(208),
    UNAUTHORIZED(490),
    PAYMENT_REQUIRED(402),
    NOT_FOUND(404),
    TIME_OUT(408),
    FAILED_DEPENDENCY(424),
    UNAVAILABLE_FOR_LEGAL_REASONS(451),
    INTERNAL_SERVER_ERROR(500),
    INTERNAL_UNHANDLED_ERROR(501),
    SENDING_TO_PROXY_EXCEPTION(601),
    SENDING_TO_APP_EXCEPTION(602),
    ACTIVATION_CODE_IS_USED(603),
    VALIDATION_ERROR(604),
    DATABASE_EXCEPTION(606),
    NOT_ALLOWED_USER_EXCEPTION(607),
    USED_GIFT_EXCEPTION(608),
    BANK_API_IS_NOT_AVAILABILE(609),
    INVALID_ACCOUNT(610),
    SMS_SENDING_FAILED_EXCEPTION(611),
    SENDING_TO_NOTIF_CENTER_FAILED(612),
    SERVICE_UNAVAILABLE(503), BAD_REQUEST(400), PARTIAL_CONTENT(206);

    private final int code;

    ResponseStatus(int i) {
        this.code = i;
    }

    public int getCode(){
        return code;
    }
}
