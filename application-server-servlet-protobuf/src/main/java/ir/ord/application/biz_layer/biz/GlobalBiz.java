package ir.ord.application.biz_layer.biz;

import ir.ord.application.accessories.Helper;
import ir.ord.application.dto.ComboElementDto;
import ir.ord.application.dto.OrderComboElementsDto;
import ir.ord.application.dto.OrderFeedbackDto;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 10/2/17.
 */
@Stateless
public class GlobalBiz {

    public List<ComboElementDto> getRescheduleReasonList() {
        List<ComboElementDto> comboElementDtoList = Helper.ComboElementHelper.getRescheduleReasonList();
        return comboElementDtoList;
    }

    public List<ComboElementDto> getCancelReasonList() {
        List<ComboElementDto> comboElementDtoList = Helper.ComboElementHelper.getCancelReasonList();
        return comboElementDtoList;
    }

    public OrderComboElementsDto getOrderFeedback() {
        OrderComboElementsDto orderComboElementsDto= new OrderComboElementsDto();
        orderComboElementsDto.setProductComboElementList(Helper.ComboElementHelper.getProductFeedbackList());
        orderComboElementsDto.setDeliveryComboElementList(Helper.ComboElementHelper.setDeliveryFeedbackList());
        orderComboElementsDto.setPackingComboElementList(Helper.ComboElementHelper.setPackingFeedbackList());
        return orderComboElementsDto;
    }
}