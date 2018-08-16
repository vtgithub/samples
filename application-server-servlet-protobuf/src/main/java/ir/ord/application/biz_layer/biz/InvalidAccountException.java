package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 4/29/17.
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException() {
    }

    public InvalidAccountException(Throwable throwable) {
        super(throwable);
    }
}
