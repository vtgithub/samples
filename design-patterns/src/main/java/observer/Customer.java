package observer;

public abstract class Customer {
    private Store store;
    private String promoteUrl;
    private String promoteTxt;

    public Customer(Store store) {
        this.store = store;
        this.store.attachCustomer(this);
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
