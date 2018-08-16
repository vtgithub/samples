package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 9/5/17.
 */
public class InitializeReturnObject {
    private String pageUrl;
    private Long amount;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public InitializeReturnObject(String pageUrl, Long amount) {
        this.pageUrl = pageUrl;
        this.amount = amount;
    }

    public InitializeReturnObject() {
    }
}
