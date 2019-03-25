package observer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Customer> customers = new ArrayList<Customer>();

    public void attachCustomer(Customer customer){
        customers.add(customer);
    }

    public void notifyAllCustomers(){
        for (Customer customer : customers) {
            if (customer instanceof CustomerClassA){
                System.out.println("customer a notified");
            }else if (customer instanceof CustomerClassB){
                System.out.println("customer b notified");
            }else if (customer instanceof CustomerClassC){
                System.out.println("customer c notified");
            }
            customer.update();
        }
    }

    public void printTestDiscountedValues(int value) {
        for (Customer customer : customers) {
            System.out.println(customer.getDiscountedValue(value));
        }
    }
}
