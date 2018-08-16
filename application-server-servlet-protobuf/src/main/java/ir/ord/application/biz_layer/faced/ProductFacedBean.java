package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.CustomValidationException;
import ir.ord.application.biz_layer.biz.ProductBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.ProductDto;
import ir.ord.application.dto.protoes.ProductProto;
import ir.ord.application.services.ResponseObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 12/23/17.
 */
@Stateless
public class ProductFacedBean {
    @EJB
    private ProductBiz productBiz;

    public ResponseObject getProductListByCatId(String catId) {
        ResponseObject responseObject = new ResponseObject();

        try {
            List<ProductDto> productDtoList= productBiz.getProductListByCatId(catId);
            Map<String, Object> data = Helper.getDictionaryFromList(productDtoList);
            responseObject.setData(data);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (CustomValidationException e) {
            responseObject.setResponseCode(ResponseStatus.VALIDATION_ERROR.getCode());
            responseObject.setMessage(e.getMessage());
            Helper.appLogger.error("getProductListByCatId:", e);
            return responseObject;
        } catch (DaoException e) {
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getProductListByCatId:", e);
            return responseObject;
        }

    }
}
