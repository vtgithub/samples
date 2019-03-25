package observer;

public class Executor {
    public static void main(String[] args) {
        Store store = new Store();
        new CustomerClassA(store);
        new CustomerClassB(store);
        new CustomerClassC(store);
        store.notifyAllCustomers();

        store.printTestDiscountedValues(100);
    }
}
