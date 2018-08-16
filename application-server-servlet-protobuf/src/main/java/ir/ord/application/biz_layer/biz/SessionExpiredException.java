package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 5/11/17.
 */
public class SessionExpiredException extends Exception {
    public SessionExpiredException() {
    }

    public SessionExpiredException(String s) {
        super(s);
    }

    public SessionExpiredException(Throwable throwable) {
        super(throwable);
    }
}
