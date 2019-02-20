package observer;

import factory_method.CustomFileReader;

public class CustomerClassA extends Customer {

    public CustomerClassA(Store store) {
        super(store);
    }

    public void update() {
        this.updatePromote("classA promote text", "classA promote url");
    }
}
