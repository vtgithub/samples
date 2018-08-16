package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 8/16/17.
 */
public class IllegalStatusChangeException extends Exception{

    public IllegalStatusChangeException() {
    }

    public IllegalStatusChangeException(String message) {
        super(message);
    }

    public IllegalStatusChangeException(Throwable throwable) {
        super(throwable);
    }
}
