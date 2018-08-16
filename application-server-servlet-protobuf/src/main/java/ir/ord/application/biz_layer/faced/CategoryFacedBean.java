package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.CategoryBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.CategoryDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@ApplicationScoped
public class CategoryFacedBean {

    @Inject
    private CategoryBiz categoryBiz;

    public ResponseObject getCategoryList() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<CategoryDto> categoryDtoList = categoryBiz.getCategoryList();
//            categoryConvertor.dtoListToBuilderList(categoryDtoList, catListBuilder);
            responseObject.setData(Helper.getDictionaryFromList(categoryDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            Helper.appLogger.error("getCategoryList:",e );
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            return responseObject;
        }
    }

    public ResponseObject getNonButtonCategory() {
        ResponseObject responseObject = new ResponseObject();
        try {
            CategoryDto categoryDto = categoryBiz.getNonButtonCategory();
            responseObject.setData(categoryDto);
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getNonButtonCategory:",e );
            return responseObject;
        }
    }

    public ResponseObject getButtonCategory() {
        ResponseObject responseObject = new ResponseObject();
        try {
            CategoryDto categoryDto = categoryBiz.getButtonCategoryList();
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            responseObject.setData(categoryDto);
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getButtonCategory:",e );
            return responseObject;
        }
    }

    public void initCategoryCache() throws CacheInitException {
        try {
            categoryBiz.initCategoryCache();
        } catch (Exception e) {

            throw new CacheInitException(e);
        }
    }
}
