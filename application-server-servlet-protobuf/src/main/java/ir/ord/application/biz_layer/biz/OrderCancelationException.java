package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 8/15/17.
 */
public class OrderCancelationException extends Exception {
    public OrderCancelationException(String orderCancelationFailed) {
    }

    public OrderCancelationException(Throwable throwable) {
        super(throwable);
    }

    public OrderCancelationException() {
    }
}
