package strategy.for_observer;

public class ExtraDiscount implements Discount {
    public double getDiscountedValue(int value) {
        return value*0.6;
    }
}
