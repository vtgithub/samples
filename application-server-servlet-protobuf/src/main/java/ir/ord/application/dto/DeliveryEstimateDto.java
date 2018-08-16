package ir.ord.application.dto;

/**
 * Created by vahid on 7/17/17.
 */
public class DeliveryEstimateDto {

    private Integer actor;
    private Long timeStamp;
    private Long deliveryTimeMin;
    private Long deliveryTimeMax;

    public Integer getActor() {
        return actor;
    }

    public void setActor(Integer actor) {
        this.actor = actor;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getDeliveryTimeMin() {
        return deliveryTimeMin;
    }

    public void setDeliveryTimeMin(Long deliveryTimeMin) {
        this.deliveryTimeMin = deliveryTimeMin;
    }

    public Long getDeliveryTimeMax() {
        return deliveryTimeMax;
    }

    public void setDeliveryTimeMax(Long deliveryTimeMax) {
        this.deliveryTimeMax = deliveryTimeMax;
    }
}
