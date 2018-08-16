package ir.ord.application.biz_layer.faced;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ResponseMessages;
import ir.ord.application.biz_layer.biz.PackageBiz;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dto.PackageDto;
import ir.ord.application.services.ResponseObject;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 5/7/17.
 */
@Stateless
public class PackageFacedBean {

    @Inject
    private PackageBiz packageBiz;

    public ResponseObject getButtonPackageList() {
        ResponseObject responseObject = new ResponseObject();
        try{
            List<PackageDto> packageDtoList =  packageBiz.getButtonPackageList();
            responseObject.setData(Helper.getDictionaryFromList(packageDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getButtonPackageList:",e );
            return responseObject;
        }
    }

    public ResponseObject getPackageListByCatId(String catId) {
        ResponseObject responseObject = new ResponseObject();
        try{
            List<PackageDto> packageDtoList =  packageBiz.getPackageListByCatId(catId);
            responseObject.setData(Helper.getDictionaryFromList(packageDtoList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getPackageListByCatId:",e );
            return responseObject;
        }
    }

    public ResponseObject getPackageList() {
        ResponseObject responseObject = new ResponseObject();
        try{
            List<PackageDto> packageList = packageBiz.getPackageList();
            responseObject.setData(Helper.getDictionaryFromList(packageList));
            responseObject.setResponseCode(ResponseStatus.OK.getCode());
            return responseObject;
        } catch (DaoException e) {

            responseObject.setResponseCode(ResponseStatus.INTERNAL_SERVER_ERROR.getCode());
            responseObject.setMessage(ResponseMessages.operationFailed);
            Helper.appLogger.error("getPackageList:",e );
            return responseObject;
        }
    }

    public void initPackageCache() throws CacheInitException {
        try {
            packageBiz.initPackageCache();
        } catch (Exception e) {

            throw new CacheInitException(e);
        }
    }
}
