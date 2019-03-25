package observer;

import strategy.for_observer.NormalDiscount;

public class CustomerClassB extends Customer {

    public CustomerClassB(Store store) {
        super(store);
        this.discount = new NormalDiscount();
    }

    public void update() {
        this.updatePromote("classB promote text", "classB promote url");
    }
}
