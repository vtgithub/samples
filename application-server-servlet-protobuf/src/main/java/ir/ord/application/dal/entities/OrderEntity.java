package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.source_entities.AppSourceEntity;
import ir.ord.application.dal.entities.source_entities.ButtonSourceEntity;
import ir.ord.application.dal.entities.source_entities.WebSourceEntity;

import java.util.List;

/**
 * Created by vahid on 4/26/17.
 */
//@Entity
//@Table
public class OrderEntity {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
//    @ElementCollection
//    private List<TimePeriodObject> timePeriodObjectList;
    private List<DeliveryEstimateObject> deliveryEstimateObjectList;
    private DeliveryEstimateObject currentDeliveryEstimate;

    private Long demandTime;

//    @ElementCollection
    private List<StatusObject> statusObjectList;
    private StatusObject currentStatus;
//    @Embedded
    private PackageEntity packageEntity;

    private Byte sourceType;

//    private String sourceName;

    private ButtonSourceEntity buttonSourceEntity;

    private AppSourceEntity appSourceEntity;

    private WebSourceEntity webSourceEntity;


//    private String funcKey;

    private String deviceToken;// if sourceType = WEB then deviceIp

    private String accountId;
//    @Embedded
    private AddressObject addressObject;

    private OptionObject rescheduleReason;
    private OptionObject cancelReason;

    private FeedBackObject productFeedBack;
    private FeedBackObject packingFeedBack;
    private FeedBackObject deliveryFeedBack;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
//
//    public List<TimePeriodObject> getTimePeriodObjectList() {
//        return timePeriodObjectList;
//    }
//
//    public void setTimePeriodObjectList(List<TimePeriodObject> timePeriodObjectList) {
//        this.timePeriodObjectList = timePeriodObjectList;
//    }


    public Long getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Long demandTime) {
        this.demandTime = demandTime;
    }


    public List<StatusObject> getStatusObjectList() {
        return statusObjectList;
    }

    public void setStatusObjectList(List<StatusObject> statusObjectList) {
        this.statusObjectList = statusObjectList;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public void setPackageEntity(PackageEntity packageEntity) {
        this.packageEntity = packageEntity;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

//    public String getFuncKey() {
//        return funcKey;
//    }
//
//    public void setFuncKey(String funcKey) {
//        this.funcKey = funcKey;
//    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AddressObject getAddressObject() {
        return addressObject;
    }

    public void setAddressObject(AddressObject addressObject) {
        this.addressObject = addressObject;
    }

    public ButtonSourceEntity getButtonSourceEntity() {
        return buttonSourceEntity;
    }

    public void setButtonSourceEntity(ButtonSourceEntity buttonSourceEntity) {
        this.buttonSourceEntity = buttonSourceEntity;
    }

    public AppSourceEntity getAppSourceEntity() {
        return appSourceEntity;
    }

    public void setAppSourceEntity(AppSourceEntity appSourceEntity) {
        this.appSourceEntity = appSourceEntity;
    }

    public WebSourceEntity getWebSourceEntity() {
        return webSourceEntity;
    }

    public void setWebSourceEntity(WebSourceEntity webSourceEntity) {
        this.webSourceEntity = webSourceEntity;
    }

    public List<DeliveryEstimateObject> getDeliveryEstimateObjectList() {
        return deliveryEstimateObjectList;
    }

    public void setDeliveryEstimateObjectList(List<DeliveryEstimateObject> deliveryEstimateObjectList) {
        this.deliveryEstimateObjectList = deliveryEstimateObjectList;
    }

    public DeliveryEstimateObject getCurrentDeliveryEstimate() {
        return currentDeliveryEstimate;
    }

    public void setCurrentDeliveryEstimate(DeliveryEstimateObject currentDeliveryEstimate) {
        this.currentDeliveryEstimate = currentDeliveryEstimate;
    }

    public StatusObject getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusObject currentStatus) {
        this.currentStatus = currentStatus;
    }

    public FeedBackObject getProductFeedBack() {
        return productFeedBack;
    }

    public void setProductFeedBack(FeedBackObject productFeedBack) {
        this.productFeedBack = productFeedBack;
    }

    public FeedBackObject getPackingFeedBack() {
        return packingFeedBack;
    }

    public void setPackingFeedBack(FeedBackObject packingFeedBack) {
        this.packingFeedBack = packingFeedBack;
    }

    public FeedBackObject getDeliveryFeedBack() {
        return deliveryFeedBack;
    }

    public void setDeliveryFeedBack(FeedBackObject deliveryFeedBack) {
        this.deliveryFeedBack = deliveryFeedBack;
    }

    public OptionObject getRescheduleReason() {
        return rescheduleReason;
    }

    public void setRescheduleReason(OptionObject rescheduleReason) {
        this.rescheduleReason = rescheduleReason;
    }

    public OptionObject getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(OptionObject cancelReason) {
        this.cancelReason = cancelReason;
    }
}
