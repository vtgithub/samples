package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 4/24/17.
 */
public class CustomValidationException extends Exception {
//    public CustomValidationException(String message) {
//        super(new Throwable(message));
//    }

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException() {
        super();
    }
}
