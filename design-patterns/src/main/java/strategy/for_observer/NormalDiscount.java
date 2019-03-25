package strategy.for_observer;

public class NormalDiscount implements Discount {
    public double getDiscountedValue(int value) {
        return value*0.1;
    }
}
