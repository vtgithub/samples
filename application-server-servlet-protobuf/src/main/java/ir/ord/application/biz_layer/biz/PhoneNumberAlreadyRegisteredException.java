package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 5/21/17.
 */
public class PhoneNumberAlreadyRegisteredException extends Exception {

    public PhoneNumberAlreadyRegisteredException() {
    }

    public PhoneNumberAlreadyRegisteredException(Throwable throwable) {
        super(throwable);
    }
}
