package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.DeliveryEstimateObject;
import ir.ord.application.dto.DeliveryEstimateDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 7/17/17.
 */
@ApplicationScoped
public class DeliveryEstimateConvertor {

    public DeliveryEstimateObject toEntity(DeliveryEstimateDto deliveryEstimateDto){
        if (deliveryEstimateDto == null)
            return null;
        DeliveryEstimateObject deliveryEstimateObject = new DeliveryEstimateObject();
        deliveryEstimateObject.setActor(deliveryEstimateDto.getActor());
        deliveryEstimateObject.setDeliveryTimeMin(deliveryEstimateDto.getDeliveryTimeMin());
        deliveryEstimateObject.setDeliveryTimeMax(deliveryEstimateDto.getDeliveryTimeMax());

        deliveryEstimateObject.setTimeStamp(deliveryEstimateDto.getTimeStamp());
        return deliveryEstimateObject;
    }

    public DeliveryEstimateDto toDto(DeliveryEstimateObject deliveryEstimateObject){
        if (deliveryEstimateObject == null)
            return null;
        DeliveryEstimateDto deliveryEstimateDto = new DeliveryEstimateDto();
        deliveryEstimateDto.setActor(deliveryEstimateObject.getActor());
        deliveryEstimateDto.setTimeStamp(deliveryEstimateObject.getTimeStamp());
        deliveryEstimateDto.setDeliveryTimeMin(deliveryEstimateObject.getDeliveryTimeMin());
        deliveryEstimateDto.setDeliveryTimeMax(deliveryEstimateObject.getDeliveryTimeMax());
        return deliveryEstimateDto;
    }

    public List<DeliveryEstimateDto> toDtoList(List<DeliveryEstimateObject> deliveryEstimateObjectList) {
        if (deliveryEstimateObjectList == null || deliveryEstimateObjectList.size()==0)
            return null;
        List<DeliveryEstimateDto> deliveryEstimateDtoList = new ArrayList<DeliveryEstimateDto>();
        for (DeliveryEstimateObject deliveryEstimateObject : deliveryEstimateObjectList) {
            deliveryEstimateDtoList.add(toDto(deliveryEstimateObject));
        }
        return deliveryEstimateDtoList;
    }
}
