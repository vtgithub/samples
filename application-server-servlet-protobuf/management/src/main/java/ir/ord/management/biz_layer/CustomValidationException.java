package ir.ord.management.biz_layer;

/**
 * Created by vahid on 4/24/17.
 */
public class CustomValidationException extends Exception {
    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException() {
        super();
    }
}
