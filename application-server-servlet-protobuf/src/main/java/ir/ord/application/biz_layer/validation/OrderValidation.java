package ir.ord.application.biz_layer.validation;


import ir.ord.application.ActorEnum;
import ir.ord.application.CommodityState;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dal.entities.DeliveryEstimateObject;
import ir.ord.application.dal.entities.StatusObject;
import ir.ord.application.dto.OrderFeedbackDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class OrderValidation {

    @Inject
    private TimePeriodValidation timePeriodValidation;
    public List<String> getOrderByIdValidation(String orderId){
        List<String> validationResult = new ArrayList<String>();
        if (orderId == null || orderId.equals(""))
            validationResult.add(ValidationMessages.orderIdEmpty);

        return validationResult;
    }

    public List<String> cancelOrderValidation( String orderId) {
        return getOrderByIdValidation(orderId);
    }

    public List<String> changeOrderTimeValidation(String orderId, Long deliveryTime, Integer fromTime, Integer toTime, Integer rescheduleReasonId, String rescheduleReasonVal) {
        List<String> validationResult = new ArrayList<String>();
        if (deliveryTime == null || deliveryTime == 0 || fromTime == null || toTime == null || fromTime == 0 || toTime == 0)
            validationResult.add(ValidationMessages.deliveryTimeEmpty);
        if (orderId == null || orderId.equals(""))
            validationResult.add(ValidationMessages.orderIdEmpty);
        if (rescheduleReasonId == null || rescheduleReasonVal == null || rescheduleReasonVal.equals(""))
            validationResult.add(ValidationMessages.comboEmpty);

        return validationResult;
    }

    public static String getOrderListValidation(String sessionId) {
        return (sessionId == null || sessionId.equals("")) ? ValidationMessages.SessionIdEmpty : null;
    }

    public List<String> getNeededAmmountValidation(String orderId) {
        List<String> validationResult = new ArrayList<String>();
        if (orderId == null || orderId.equals(""))
            validationResult.add(ValidationMessages.orderIdEmpty);
        return validationResult;
    }

    public List<String> changeOrderStatusValidation(String orderId, String status) {
        List<String> validationResult = new ArrayList<String>();
        if (orderId == null || orderId.equals(""))
            validationResult.add(ValidationMessages.orderIdEmpty);
        if (status == null || status.equals(""))
            validationResult.add(ValidationMessages.uriIsWrong);
        return validationResult;
    }

    public List<String> getchangeTimePeriodList(String orderId) {
        return getOrderByIdValidation(orderId);
    }



    public Boolean validRescheduleState(StatusObject currentStatus) {
        if (
                currentStatus.getState() < CommodityState.PAID.getCode() ||
                currentStatus.getState() == CommodityState.UNPAID.getCode() ||
                currentStatus.getState() >= CommodityState.WAREHOUSE_CONFIRMED.getCode()
            )
            return false;
        return true;
    }

    public Boolean rescheduledByUser(List<DeliveryEstimateObject> deliveryEstimateObjectList){
        if (deliveryEstimateObjectList == null || deliveryEstimateObjectList.size() == 0)
            return false;
        for (DeliveryEstimateObject deliveryEstimateObject : deliveryEstimateObjectList) {
            if (deliveryEstimateObject.getActor() == ActorEnum.USER.getCode())
                return true;
        }
        return false;
    }

    public Boolean canBeCancel(Integer state) {
        if ( state >= CommodityState.WAREHOUSE_PROCESSING.getCode() || state == CommodityState.CANCELED.getCode() )
            return false;
        return true;
    }


    public List<String> getOrderFeedbackValidation(String orderId, OrderFeedbackDto orderFeedbackDto) {
        List<String> validationResult = getOrderFeedbackDtoValidation(orderFeedbackDto);
        if (orderId == null || orderId.equals(""))
            validationResult.add(ValidationMessages.orderIdEmpty);
        return validationResult;
    }

    public List<String> getOrderFeedbackDtoValidation(OrderFeedbackDto orderFeedbackDto){
        List<String> validationResult = new ArrayList<String>();
        if (orderFeedbackDto == null || orderFeedbackDto.getDeliveryFeedback() == null || orderFeedbackDto.getPackingFeedback() == null || orderFeedbackDto.getProductFeedback() == null)
            validationResult.add(ValidationMessages.orderFeedbackEmpty);
        return validationResult;
    }

    public boolean orderStatusListValidation(List<Integer> statusList) {
//        List<String> validationResult = new ArrayList<String>();
        boolean isUnresolvedOrder = statusList.contains(CommodityState.REQUESTED.getCode()) ||
                statusList.contains(CommodityState.PAID.getCode()) ||
                statusList.contains(CommodityState.USER_CONFIRMED.getCode()) ||
                statusList.contains(CommodityState.WAREHOUSE_PENDING.getCode()) ||
                statusList.contains(CommodityState.WAREHOUSE_PROCESSING.getCode()) ||
                statusList.contains(CommodityState.WAREHOUSE_CONFIRMED.getCode()) ||
                statusList.contains(CommodityState.LOGISTIC_PENDING.getCode()) ||
                statusList.contains(CommodityState.SENT.getCode());
//                statusList.contains(CommodityState.DELIVERED.getCode()) ||
//                statusList.contains(CommodityState.UNDELIVERED.getCode()))

        return isUnresolvedOrder;
    }
}
