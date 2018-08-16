package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.TimePeriodBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.TimePeriodDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vahid on 6/28/17.
 */
@Stateless
public class TimePeriodFacedBean {

    @EJB
    private TimePeriodBiz timePeriodBiz;

    public ResponseObject getTimePeriodList() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<TimePeriodDto> timePeriodDtoList = timePeriodBiz.getTimePeriodList();
            responseObject.setData(Helper.getDictionaryFromList(timePeriodDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getTimePeriodList:",e );
            return responseObject;
        }
    }

//    public ResponseObject getCustomTimePeriodList(long baseTime, int futureDays) {
//        ResponseObject responseObject = new ResponseObject();
//
//        try {
//            List<TimePeriodDto> timePeriodDtoList = timePeriodBiz.getCustomTimePeriodList(baseTime, futureDays);
//            responseObject.setData(Helper.getDictionaryFromList(timePeriodDtoList));
//            responseObject.setResponseCode(ResponseStatus.OK.getCode());
//            return responseObject;
//        } catch (DaoException e) {
//
//            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
//            responseObject.setMessage(ResponseMessages.operationFailed);
//            Helper.appLogger.error("getCustomTimePeriodList:",e );
//            return responseObject;
//        }
//
//    }
}
