package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.biz.GlobalBiz;
import ir.ord.application.dto.ComboElementDto;
import ir.ord.application.dto.OrderComboElementsDto;
import ir.ord.application.dto.OrderFeedbackDto;
import ir.ord.application.services.ResponseObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 10/2/17.
 */
@Stateless
public class GlobalFacedBean {

    @EJB
    private GlobalBiz globalBiz;

    public ResponseObject getRescheduleReasonList() {
        ResponseObject responseObject = new ResponseObject();
        List<ComboElementDto> comboElementDtoList = globalBiz.getRescheduleReasonList();
        responseObject.setResponseCode(ResponseStatus.OK.getCode());
        responseObject.setData(Helper.getDictionaryFromList(comboElementDtoList));
        return responseObject;
    }

    public ResponseObject getCancelReasonList() {
        ResponseObject responseObject = new ResponseObject();
        List<ComboElementDto> cancelReasonList = globalBiz.getCancelReasonList();
        responseObject.setData(Helper.getDictionaryFromList(cancelReasonList));
        responseObject.setResponseCode(ResponseStatus.OK.getCode());
        return responseObject;
    }

    public ResponseObject getOrderFeedBack() {
        ResponseObject responseObject = new ResponseObject();
        OrderComboElementsDto orderComboElementsDto = globalBiz.getOrderFeedback();
        responseObject.setData(orderComboElementsDto);
        responseObject.setResponseCode(ResponseStatus.OK.getCode());
        return responseObject;
    }
}
