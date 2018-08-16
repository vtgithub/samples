package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.*;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.OrderDto;
import ir.ord.application.dto.OrderFeedbackDto;
import ir.ord.application.dto.OrderListItemDto;
import ir.ord.application.dto.TimePeriodDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 5/3/17.
 */
@Stateless
public class OrderFacedBean {

    @EJB
    private OrderBiz orderBiz;

    public ResponseObject getOrderById(String orderId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            OrderDto orderDto = orderBiz.getOrderById(orderId);
            responseObject.setData(orderDto);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getOrderById:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getOrderById:",e );
            return responseObject;
        } catch (IOException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getOrderById:",e );
            return responseObject;
        }
    }

    public ResponseObject getOrderList(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
                List<OrderListItemDto> orderListItemDtos = orderBiz.getOrderList(sessionId);
                responseObject.setData(Helper.getDictionaryFromList(orderListItemDtos));
                responseObject.setResponseCode(ResponseStatus.OK.getCode());
                return responseObject;

        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getOrderList:",e );
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getOrderList:",e );
            return responseObject;
        }catch (IOException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getOrderById:",e );
            return responseObject;
        }
    }

    public ResponseObject cancelOrder(String sessionId, String orderId, Integer cancelReasonId, String cancelReasonVal) {
        ResponseObject responseObject = new ResponseObject();
        try {
            OrderDto orderDto = orderBiz.cancelOrder(sessionId, orderId, cancelReasonId, cancelReasonVal);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(orderDto);
            responseObject.setMessage(ResponseMessages.orderCancelationDone);
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("cancelOrder:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("cancelOrder:",e );
            return responseObject;
        } catch (IOException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.conversionFailed);
            Helper.appLogger.error("cancelOrder:", e);
            return  responseObject;
        } catch (IllegalStatusChangeException e) {

            responseObject.setResponseCode(ResponseStatus.METHOD_NOT_ALLOWED.getCode());
            responseObject.setMessage(ResponseMessages.orderCancelationFailed);
            Helper.appLogger.error("cancelOrder:", e);
            return  responseObject;
        }
    }

    public ResponseObject changeOrderTime(String orderId, Long baseTime, Integer fromTime, Integer toTime, Integer rescheduleReasonId, String rescheduleReasonVal) {
        ResponseObject responseObject = new ResponseObject();
        try {
            OrderDto orderDto = orderBiz.changeOrderTime(orderId, baseTime, fromTime, toTime, rescheduleReasonId, rescheduleReasonVal);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.orderTimeChangeComplete);
            responseObject.setData(orderDto);
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("changeOrderTime:",e );
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeOrderTime:",e );
            return responseObject;
        } catch (IOException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeOrderTime:",e );
            return responseObject;
        }
    }


    public ResponseObject getOrderCount(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Long orderCount = orderBiz.getOrderCount(sessionId);
            Map<String, Long> orderCountMap = new HashMap<String, Long>();
            orderCountMap.put("count", orderCount);
            responseObject.setData(orderCountMap);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getOrderCount:",e );
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getOrderCount:",e );
            return responseObject;
        }
    }

    public ResponseObject cleanAllOrders() {
        ResponseObject responseObject = new ResponseObject();
        try {
            orderBiz.cleanAllOrders();
            responseObject.setMessage(ResponseMessages.cleanComplete);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("cleanAllOrders:",e );
            return responseObject;
        }
    }

    public ResponseObject getNeededAmount(String orderId, String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Double neededAmount = orderBiz.getNeededAmount(orderId, sessionId);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            Map<String, Double> data = new HashMap<String, Double>();
            data.put("neededAmount", neededAmount);
            responseObject.setData(data);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getNeededAmount:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getNeededAmount:",e );
            return responseObject;
        }
    }

    public ResponseObject changeOrderStatus(String orderId, String status) {
        ResponseObject responseObject = new ResponseObject();
        try {
            OrderDto orderDto= orderBiz.changeOrderStatus(orderId, status);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(orderDto);
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("changeOrderStatus:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeOrderStatus:",e );
            return responseObject;
        } catch (IOException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeOrderStatus:",e );
            return responseObject;
        } catch (IllegalStatusChangeException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("changeOrderStatus:",e );
            return responseObject;
        }
    }


    public ResponseObject getChangeTimePeriodList(String orderId) {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<TimePeriodDto> timePeriodDtoList = orderBiz.getChangeTimePeriodList(orderId);
            responseObject.setData(Helper.getDictionaryFromList(timePeriodDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getChangeTimePeriodList:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getChangeTimePeriodList:",e );
            return responseObject;
        }
    }

    public ResponseObject submitOrderFeedback(String orderId, OrderFeedbackDto orderFeedbackDto) {
        ResponseObject responseObject = new ResponseObject();
        try {
            orderBiz.submitOrderFeedback(orderId, orderFeedbackDto);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setMessage(ResponseMessages.infoInsertionComplete);
            return responseObject;
        } catch (CustomValidationException e) {

            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("submitOrderFeedback:",e );
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("submitOrderFeedback:",e );
            return responseObject;
        }
    }

    public ResponseObject getUnresolvedOrderList(String sessionId) {
        ResponseObject responseObject = new ResponseObject();
        try{
//            List<Integer> statusList = new ArrayList<Integer>();
            List<OrderListItemDto> orderDtoList = orderBiz.getUnresolvedOrderList(sessionId);
//            List<OrderListItemDto> orderDtoList = orderBiz.getSpecificStatusesOrderList(sessionId, statusList);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(Helper.getDictionaryFromList(orderDtoList));
//            responseObject.setMessage(ResponseMessages.infoInsertionComplete);
            return responseObject;
        }catch (DaoException e){
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getUnresolvedOrderList:",e );
            return responseObject;
        } catch (IOException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getUnresolvedOrderList:",e );
            return responseObject;
        }
    }
}
