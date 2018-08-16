package ir.ord.warehouse.biz_layer.biz;

import ir.ord.warehouse.biz_layer.rpc.OrderStruct;
import ir.ord.warehouse.dto.CustomOrderDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 9/20/17.
 */
@ApplicationScoped
public class OrderConverter {
    public CustomOrderDto getDto(OrderStruct orderStruct){
        if (orderStruct == null)
            return null;
        CustomOrderDto customOrderDto = new CustomOrderDto();
        customOrderDto.setPackageName(orderStruct.getPackageName());
        customOrderDto.setPackageId(orderStruct.getPackageId());
        customOrderDto.setId(orderStruct.getId());
        return customOrderDto;
    }


    public List<CustomOrderDto> getDtoList(List<OrderStruct> orderList) {
        List<CustomOrderDto> customOrderDtoList = new ArrayList<CustomOrderDto>();
        if (orderList == null)
            return customOrderDtoList;
        for (OrderStruct orderStruct : orderList) {
            customOrderDtoList.add(getDto(orderStruct));
        }
        return customOrderDtoList;
    }
}
