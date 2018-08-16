package ir.ord.application.dto;

import ir.ord.application.dto.source_dtos.AppSourceDto;
import ir.ord.application.dto.source_dtos.ButtonSourceDto;
import ir.ord.application.dto.source_dtos.WebSourceDto;

import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
public class OrderDto {
    private String id;
    private List<DeliveryEstimateDto> deliveryEstimateDtoList;
    private DeliveryEstimateDto  currentDeliveryEstimate;
    private Long demandTime;
    private List<StatusObjectDto> statusObjectDtoList;
    private StatusObjectDto currentStatus;
    private PackageDto packageDto;
    private Byte sourceType;
    private ButtonSourceDto buttonSourceDto;
    private AppSourceDto appSourceDto;
    private WebSourceDto webSourceDto;
    private String deviceToken;
    private String addressTitle;
    private AddressDto addressDto;
    private Boolean canReschedule;
    private Boolean canBeCancel;
    private Boolean canFeedBack;
    private ComboElementDto rescheduleReason;
    private ComboElementDto cancelReason;
    private FeedbackObjectDto productFeedBack;
    private FeedbackObjectDto packingFeedBack;
    private FeedbackObjectDto deliveryFeedBack;

    public Boolean getCanFeedBack() {
        return canFeedBack;
    }

    public void setCanFeedBack(Boolean canFeedBack) {
        this.canFeedBack = canFeedBack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public List<TimePeriodObjectDto> getTimePeriodObjectDtoList() {
//        return timePeriodObjectDtoList;
//    }
//
//    public void setTimePeriodObjectDtoList(List<TimePeriodObjectDto> timePeriodObjectDtoList) {
//        this.timePeriodObjectDtoList = timePeriodObjectDtoList;
//    }



    public Long getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Long demandTime) {
        this.demandTime = demandTime;
    }


    public List<StatusObjectDto> getStatusObjectDtoList() {
        return statusObjectDtoList;
    }

    public void setStatusObjectDtoList(List<StatusObjectDto> statusObjectDtoList) {
        this.statusObjectDtoList = statusObjectDtoList;
    }

    public ButtonSourceDto getButtonSourceDto() {
        return buttonSourceDto;
    }

    public void setButtonSourceDto(ButtonSourceDto buttonSourceDto) {
        this.buttonSourceDto = buttonSourceDto;
    }

    public AppSourceDto getAppSourceDto() {
        return appSourceDto;
    }

    public void setAppSourceDto(AppSourceDto appSourceDto) {
        this.appSourceDto = appSourceDto;
    }

    public WebSourceDto getWebSourceDto() {
        return webSourceDto;
    }

    public void setWebSourceDto(WebSourceDto webSourceDto) {
        this.webSourceDto = webSourceDto;
    }

    public PackageDto getPackageDto() {
        return packageDto;
    }

    public void setPackageDto(PackageDto packageDto) {
        this.packageDto = packageDto;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }



    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public List<DeliveryEstimateDto> getDeliveryEstimateDtoList() {
        return deliveryEstimateDtoList;
    }

    public void setDeliveryEstimateDtoList(List<DeliveryEstimateDto> deliveryEstimateDtoList) {
        this.deliveryEstimateDtoList = deliveryEstimateDtoList;
    }

    public DeliveryEstimateDto getCurrentDeliveryEstimate() {
        return currentDeliveryEstimate;
    }

    public void setCurrentDeliveryEstimate(DeliveryEstimateDto currentDeliveryEstimate) {
        this.currentDeliveryEstimate = currentDeliveryEstimate;
    }

    public StatusObjectDto getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusObjectDto currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Boolean getCanReschedule() {
        return canReschedule;
    }

    public void setCanReschedule(Boolean canReschedule) {
        this.canReschedule = canReschedule;
    }

    public Boolean getCanBeCancel() {
        return canBeCancel;
    }

    public void setCanBeCancel(Boolean canBeCancel) {
        this.canBeCancel = canBeCancel;
    }

    public FeedbackObjectDto getProductFeedBack() {
        return productFeedBack;
    }

    public void setProductFeedBack(FeedbackObjectDto productFeedBack) {
        this.productFeedBack = productFeedBack;
    }

    public FeedbackObjectDto getPackingFeedBack() {
        return packingFeedBack;
    }

    public void setPackingFeedBack(FeedbackObjectDto packingFeedBack) {
        this.packingFeedBack = packingFeedBack;
    }

    public FeedbackObjectDto getDeliveryFeedBack() {
        return deliveryFeedBack;
    }

    public void setDeliveryFeedBack(FeedbackObjectDto deliveryFeedBack) {
        this.deliveryFeedBack = deliveryFeedBack;
    }

    public ComboElementDto getRescheduleReason() {
        return rescheduleReason;
    }

    public void setRescheduleReason(ComboElementDto rescheduleReason) {
        this.rescheduleReason = rescheduleReason;
    }

    public ComboElementDto getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(ComboElementDto cancelReason) {
        this.cancelReason = cancelReason;
    }
}
