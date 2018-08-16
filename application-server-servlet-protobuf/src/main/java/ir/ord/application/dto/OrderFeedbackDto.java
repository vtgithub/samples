package ir.ord.application.dto;

import java.util.List;

/**
 * Created by vahid on 10/3/17.
 */
public class OrderFeedbackDto {
    private FeedbackObjectDto productFeedback;
    private FeedbackObjectDto packingFeedback;
    private FeedbackObjectDto deliveryFeedback;

    public FeedbackObjectDto getProductFeedback() {
        return productFeedback;
    }

    public void setProductFeedback(FeedbackObjectDto productFeedback) {
        this.productFeedback = productFeedback;
    }

    public FeedbackObjectDto getPackingFeedback() {
        return packingFeedback;
    }

    public void setPackingFeedback(FeedbackObjectDto packingFeedback) {
        this.packingFeedback = packingFeedback;
    }

    public FeedbackObjectDto getDeliveryFeedback() {
        return deliveryFeedback;
    }

    public void setDeliveryFeedback(FeedbackObjectDto deliveryFeedback) {
        this.deliveryFeedback = deliveryFeedback;
    }
}
