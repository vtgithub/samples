package ir.ord.management.biz_layer.biz;

import com.google.protobuf.InvalidProtocolBufferException;
import ir.ord.management.RPCEntityEnum;
import ir.ord.management.biz_layer.CustomValidationException;
import ir.ord.management.biz_layer.validation.ManagementValidation;
import ir.ord.management.dal.dao.RemoteDao;
import ir.ord.management.dal.rpc.RemoteDaoException;
import ir.ord.management.dto.protoes.CategoryProto;
import ir.ord.management.dto.protoes.GiftProto;
import ir.ord.management.dto.protoes.PackageProto;
import ir.ord.management.dto.protoes.TimePeriodProto;
import org.apache.thrift.TException;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@Stateless
public class ManagementBiz {

    @Inject
    private RemoteDao remoteDao;
    @Inject
    private ManagementValidation managementValidation;


    public void addCategory(CategoryProto.CategoryRequest.Builder categoryRequestBuilder) throws CustomValidationException, RemoteDaoException {
        List<String> validationResultList = managementValidation.addCategoryValidation(categoryRequestBuilder);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        remoteDao.update(
                categoryRequestBuilder.getId(),
                categoryRequestBuilder.build().toByteArray(),
                RPCEntityEnum.CATEGORY_ENTITY
        );
    }

    public void addPackage(PackageProto.PackageRequest.Builder packageRequestBuilder) throws CustomValidationException, RemoteDaoException {
        List<String> validationResult = managementValidation.addPackagevalidation(packageRequestBuilder);
        if (validationResult.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));

        remoteDao.save(
                packageRequestBuilder.build().toByteArray(),
                RPCEntityEnum.PACKAGE_ENTITY
        );

    }


    public void addTimePeriod(TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder) throws CustomValidationException, RemoteDaoException {
        List<String> validationResultlist = managementValidation.addTimePeriodValiadtion(timePeriodRequestBuilder);
        if (validationResultlist.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultlist, ", "));
        remoteDao.save(
                timePeriodRequestBuilder.build().toByteArray(),
                RPCEntityEnum.TIME_PERIOD_ENTITY
        );

    }


    public void fillTimePeriodList(TimePeriodProto.TimePeriodFillRequest.Builder builder) throws RemoteDaoException {
        remoteDao.fillTimePeriodList(builder.build().toByteArray(), RPCEntityEnum.TIME_PERIOD_ENTITY);
    }


    public void addGift(GiftProto.GiftRequest.Builder giftRequestBuilder) throws CustomValidationException, RemoteDaoException {
        List<String> validationResult = managementValidation.addGiftValidation(giftRequestBuilder);
        if (validationResult.size() > 0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        remoteDao.save(
                giftRequestBuilder.build().toByteArray(),
                RPCEntityEnum.GIFT_ENTITY
        );
    }

    public GiftProto.GiftList.Builder getAllGift() throws RemoteDaoException {
        try{
            byte[] giftListBytes = remoteDao.getAll(RPCEntityEnum.GIFT_ENTITY);
            GiftProto.GiftList.Builder giftListBuilder = GiftProto.GiftList.newBuilder();
            giftListBuilder.mergeFrom(giftListBytes);
            return giftListBuilder;
        }catch (InvalidProtocolBufferException e){
            throw new RemoteDaoException(e.getMessage());
        }

    }
}
