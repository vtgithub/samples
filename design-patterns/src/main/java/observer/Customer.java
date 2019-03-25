package observer;

import strategy.for_observer.Discount;

public abstract class Customer {
    private Store store;
    private String promoteUrl;
    private String promoteTxt;
    protected Discount discount;


    public Customer(Store store) {
        this.store = store;
        this.store.attachCustomer(this);
    }

    public void setDiscountAlgo(Discount discount){
        this.discount = discount;
    }

    public double getDiscountedValue(int value){
        return discount.getDiscountedValue(value);
    }

    protected void updatePromote(String promoteTxt, String promoteUrl){
        this.promoteTxt = promoteTxt;
        this.promoteUrl = promoteUrl;
        System.out.println(toString());
    }
    public abstract void update();

    @Override
    public String toString() {
        return "Customer{" +
                "promoteUrl='" + promoteUrl + '\'' +
                ", promoteTxt='" + promoteTxt + '\'' +
                '}';
    }
}
