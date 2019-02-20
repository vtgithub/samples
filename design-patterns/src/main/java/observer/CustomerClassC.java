package observer;

public class CustomerClassC extends Customer {

    public CustomerClassC(Store store) {
        super(store);
    }

    public void update() {
        this.updatePromote("classC promote text", "classC promote url");
    }
}
