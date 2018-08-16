package ir.ord.application.Convertor;

import ir.ord.application.ActorEnum;
import ir.ord.application.CommodityState;
import ir.ord.application.Convertor.sources_convertors.AppSourceConvertor;
import ir.ord.application.Convertor.sources_convertors.ButtonSourceConvertor;
import ir.ord.application.Convertor.sources_convertors.WebSourceConvertor;
import ir.ord.application.SourceTypeEnum;
import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.rpc.OrderStruct;
import ir.ord.application.biz_layer.validation.OrderValidation;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.entities.DeliveryEstimateObject;
import ir.ord.application.dal.entities.OrderEntity;
import ir.ord.application.dal.entities.StatusObject;
import ir.ord.application.dto.OrderDto;
import ir.ord.application.dto.OrderListItemDto;
import ir.ord.application.dto.protoes.OrderProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class OrderConvertor {

    @Inject
    private TimePeriodObjectConvertor timePeriodObjectConvertor;
    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @Inject
    private PackageConvertor packageConvertor;
    @Inject
    private AddressConvertor addressConvertor;
    @Inject
    private DeliveryEstimateConvertor deliveryEstimateConvertor;
    @Inject
    private ButtonSourceConvertor buttonSourceConvertor;
    @Inject
    private AppSourceConvertor appSourceConvertor;
    @Inject
    private WebSourceConvertor webSourceConvertor;
    @Inject
    private FeedBackConverter feedBackConverter;
    @Inject
    private OrderValidation orderValidation;
    @Inject
    private OptionObjectConverter optionObjectConverter;

    public OrderDto getDto(OrderEntity orderEntity) throws DaoException, IOException {
        if (orderEntity == null)
            return null;
        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.get_id());
        orderDto.setDeliveryEstimateDtoList(
                deliveryEstimateConvertor.toDtoList(orderEntity.getDeliveryEstimateObjectList())
        );
        orderDto.setCurrentDeliveryEstimate(
                deliveryEstimateConvertor.toDto(orderEntity.getCurrentDeliveryEstimate())
        );
        orderDto.setDemandTime(orderEntity.getDemandTime());
        orderDto.setStatusObjectDtoList(
                statusObjectConvertor.getDtoListForOrder(orderEntity.getStatusObjectList())
        );
        if (orderEntity.getCurrentStatus() != null) {
            orderDto.setCurrentStatus(
                    statusObjectConvertor.getCurrentStatusObjectDto(orderDto.getStatusObjectDtoList())
            );
        }
        orderDto.setPackageDto(
                packageConvertor.getDto(orderEntity.getPackageEntity())
        );
        orderDto.setSourceType(orderEntity.getSourceType());
        orderDto.setDeviceToken(orderEntity.getDeviceToken());
        orderDto.setButtonSourceDto(buttonSourceConvertor.getDto(orderEntity.getButtonSourceEntity()));
        orderDto.setAppSourceDto(appSourceConvertor.getDto(orderEntity.getAppSourceEntity()));
        orderDto.setWebSourceDto(webSourceConvertor.getDto(orderEntity.getWebSourceEntity()));
//        orderDto.setFuncKey(orderEntity.getFuncKey());
//        orderDto.setAccountId(orderEntity.getAccountId());
        orderDto.setAddressDto(addressConvertor.getDto(orderEntity.getAddressObject()));
        orderDto.setCanBeCancel(orderValidation.canBeCancel(orderEntity.getCurrentStatus().getState()));
        orderDto.setCanReschedule(
                orderValidation.validRescheduleState(orderEntity.getCurrentStatus()) &&
                !orderValidation.rescheduledByUser(orderEntity.getDeliveryEstimateObjectList())
        );
        orderDto.setDeliveryFeedBack(feedBackConverter.getDto(orderEntity.getDeliveryFeedBack()));
        orderDto.setPackingFeedBack(feedBackConverter.getDto(orderEntity.getPackingFeedBack()));
        orderDto.setProductFeedBack(feedBackConverter.getDto(orderEntity.getProductFeedBack()));
        orderDto.setCanFeedBack(orderEntity.getCurrentStatus().getState() == CommodityState.DELIVERED.getCode());
        if (orderEntity.getRescheduleReason() != null)
            orderDto.setRescheduleReason(optionObjectConverter.getDto(orderEntity.getRescheduleReason()));
        if (orderEntity.getCancelReason() != null)
            orderDto.setCancelReason(optionObjectConverter.getDto(orderEntity.getCancelReason()));
        return orderDto;
    }




    public List<OrderListItemDto> getListItemDtoList(List<OrderEntity> orderEntityList) throws DaoException, IOException {
        if (orderEntityList == null)
            return null;

        List<OrderListItemDto> orderListItemDtos = new ArrayList<OrderListItemDto>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderListItemDto listItemDto = getListItemDto(orderEntity);
            if (listItemDto != null)
                orderListItemDtos.add(listItemDto);
        }
        return orderListItemDtos;
    }

    private OrderListItemDto getListItemDto(OrderEntity orderEntity) {
        if (orderEntity == null)
            return null;
        OrderListItemDto orderListItemDto = new OrderListItemDto();
        orderListItemDto.setId(orderEntity.get_id());
        orderListItemDto.setCurrentDeliveryEstimate(
                deliveryEstimateConvertor.toDto(orderEntity.getCurrentDeliveryEstimate())
        );
        orderListItemDto.setDemandTime(orderEntity.getDemandTime());
        if (orderEntity.getCurrentStatus() != null) {

            orderListItemDto.setCurrentStatus(
                    statusObjectConvertor.getCurrentUserReadableStatusObjectDto(orderEntity.getStatusObjectList())
            );
        }
        if (orderEntity.getPackageEntity() != null){
            orderListItemDto.setPackageName(orderEntity.getPackageEntity().getName());
            orderListItemDto.setPackagePrice(orderEntity.getPackageEntity().getPrice());
        }
        orderListItemDto.setSourceType(orderEntity.getSourceType());
        // set sourceName
        if (orderEntity.getSourceType() == SourceTypeEnum.BUTTON.getCode()) {
            orderListItemDto.setSourceName(orderEntity.getButtonSourceEntity().getOrdButton().getName());
            orderListItemDto.setSourceId(orderEntity.getButtonSourceEntity().getOrdButton().get_id());
        }else if (orderEntity.getSourceType() == SourceTypeEnum.APP.getCode()){
            orderListItemDto.setSourceName(String.valueOf(orderEntity.getAppSourceEntity().getDevicePlatform()));
            orderListItemDto.setSourceId(String.valueOf(orderEntity.getAppSourceEntity().getDevicePlatform()));
        }else if (orderEntity.getSourceType() == SourceTypeEnum.WEB.getCode()){
            orderListItemDto.setSourceName(orderEntity.getWebSourceEntity().getIp());
            orderListItemDto.setSourceId("0");
        }
//        orderListItemDto.setDeviceToken(orderEntity.getDeviceToken());
        if (orderEntity.getAddressObject() != null) {
            orderListItemDto.setAddressTitle(orderEntity.getAddressObject().getTitle());
            orderListItemDto.setAddressId(orderEntity.getAddressObject().get_id());
        }
        return orderListItemDto;
    }



    public List<OrderStruct> getOrderStructList(List<OrderEntity> orderWarehousePendingList) {
        if (orderWarehousePendingList == null || orderWarehousePendingList.size() == 0)
            return null;
        List<OrderStruct> orderStructList = new ArrayList<OrderStruct>();
        for (OrderEntity orderEntity : orderWarehousePendingList) {
            OrderStruct orderStruct = getOrderStruct(orderEntity);
            if (orderStruct != null)
                orderStructList.add(orderStruct);
        }
        return orderStructList;
    }

    private OrderStruct getOrderStruct(OrderEntity orderEntity) {
        if (orderEntity == null)
            return null;
        OrderStruct orderStruct = new OrderStruct();
        orderStruct.setId(orderEntity.get_id());
        orderStruct.setPackageId(orderEntity.getPackageEntity().get_id());
        orderStruct.setPackageName(orderEntity.getPackageEntity().getName());
        return orderStruct;
    }


    //    public OrderProto.OrderList.Builder getListItemListBuilder(List<OrderEntity> orderEntityList) throws IOException, DaoException {
//        OrderProto.OrderList.Bui
//        List<OrderListItemDto> listItemDtoList = getListItemDtoList(orderEntityList);
//        if (listItemDtoList == null)
//            return null;
//        Map<String, Object> list = Helper.getDictionaryFromList(listItemDtoList);
//        JsonFormat.parser().merge(list, );
//    }
}
