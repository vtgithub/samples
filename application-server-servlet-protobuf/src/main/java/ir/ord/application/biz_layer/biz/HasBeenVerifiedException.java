package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 8/1/17.
 */
public class HasBeenVerifiedException extends Exception {
    public HasBeenVerifiedException() {
    }

    public HasBeenVerifiedException(Throwable throwable) {
        super(throwable);
    }

    public HasBeenVerifiedException(String s) {
        super(s);
    }
}
