package observer;

public class CustomerClassB extends Customer {

    public CustomerClassB(Store store) {
        super(store);
    }

    public void update() {
        this.updatePromote("classB promote text", "classB promote url");
    }
}
