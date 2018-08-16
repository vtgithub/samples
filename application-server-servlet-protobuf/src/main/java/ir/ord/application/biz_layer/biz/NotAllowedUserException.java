package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 5/3/17.
 */
public class NotAllowedUserException extends Exception {
    public NotAllowedUserException() {
    }

    public NotAllowedUserException(Throwable throwable) {
        super(throwable);
    }
}
