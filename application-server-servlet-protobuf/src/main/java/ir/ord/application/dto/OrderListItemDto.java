package ir.ord.application.dto;

/**
 * Created by vahid on 7/31/17.
 */
public class OrderListItemDto {
    private String id;
    private DeliveryEstimateDto currentDeliveryEstimate;
    private Long demandTime;
    private StatusObjectDto currentStatus;
    private String packageName;
    private Double packagePrice;
    private Byte sourceType;
    private String sourceName;
    private String sourceId;
//    private String deviceToken;
    private String addressTitle;
    private String addressId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeliveryEstimateDto getCurrentDeliveryEstimate() {
        return currentDeliveryEstimate;
    }

    public void setCurrentDeliveryEstimate(DeliveryEstimateDto currentDeliveryEstimate) {
        this.currentDeliveryEstimate = currentDeliveryEstimate;
    }

    public Long getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Long demandTime) {
        this.demandTime = demandTime;
    }

    public StatusObjectDto getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusObjectDto currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
//
//    public String getDeviceToken() {
//        return deviceToken;
//    }
//
//    public void setDeviceToken(String deviceToken) {
//        this.deviceToken = deviceToken;
//    }


    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public Double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
