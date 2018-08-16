package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 5/22/17.
 */
public class NoQRCodeException extends Exception {
    public NoQRCodeException() {
    }

    public NoQRCodeException(Throwable throwable) {
        super(throwable);
    }
}
