package observer;

import factory_method.CustomFileReader;
import strategy.for_observer.ExtraDiscount;

public class CustomerClassA extends Customer {

    public CustomerClassA(Store store) {
        super(store);
        this.discount = new ExtraDiscount();
    }

    public void update() {
        this.updatePromote("classA promote text", "classA promote url");
    }
}
