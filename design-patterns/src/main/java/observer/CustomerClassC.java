package observer;

import strategy.for_observer.NormalDiscount;

public class CustomerClassC extends Customer {

    public CustomerClassC(Store store) {
        super(store);
        this.discount = new NormalDiscount();
    }

    public void update() {
        this.updatePromote("classC promote text", "classC promote url");
    }
}
