package ir.ord.application.biz_layer.rpc;

import ir.ord.application.Convertor.CategoryConvertor;
import ir.ord.application.Convertor.GiftConvertor;
import ir.ord.application.Convertor.PackageConvertor;
import ir.ord.application.Convertor.TimePeriodConvertor;
import ir.ord.application.RPCEntityEnum;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.GiftEntity;
import ir.ord.application.dal.entities.PackageEntity;
import ir.ord.application.dal.entities.TimePeriodEntity;
import ir.ord.application.dto.protoes.CategoryProto;
import ir.ord.application.dto.protoes.GiftProto;
import ir.ord.application.dto.protoes.PackageProto;
import ir.ord.application.dto.protoes.TimePeriodProto;



import org.apache.thrift.TException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by vahid on 7/23/17.
 */
@ApplicationScoped
public class CacheServiceHandler implements CacheService.Iface {

    @Inject
    CategoryDao categoryDao;
    @Inject
    PackageDao packageDao;
    @Inject
    TimePeriodDao timePeriodDao;
    @Inject
    GiftDao giftDao;
    @Inject
    CacheDao cacheDao;
    @Inject
    CategoryConvertor categoryConvertor;
    @Inject
    PackageConvertor packageConvertor;
    @Inject
    TimePeriodConvertor timePeriodConvertor;
    @Inject
    GiftConvertor giftConvertor;

    public void save(ByteBuffer entity, RPCEntityEnum entityType) throws RemoteDaoException, TException {
        try {
            if (entityType == RPCEntityEnum.CATEGORY_ENTITY){
                CategoryProto.CategoryRequest.Builder catBuilder = CategoryProto.CategoryRequest.newBuilder();
                CategoryEntity categoryEntity = categoryConvertor.toEntity(catBuilder.mergeFrom(entity.array()));
                categoryDao.save(categoryEntity);
                cacheDao.clearCategoryCache();
            }else if (entityType == RPCEntityEnum.PACKAGE_ENTITY){
                PackageProto.PackageRequest.Builder packBuilder = PackageProto.PackageRequest.newBuilder();
                PackageEntity packageEntity = packageConvertor.getEntity(packBuilder.mergeFrom(entity.array()));
                packageDao.save(packageEntity);
                cacheDao.clearPackageCache();
            }else if (entityType == RPCEntityEnum.TIME_PERIOD_ENTITY){
                TimePeriodProto.TimePeriodRequest.Builder timeBuilder = TimePeriodProto.TimePeriodRequest.newBuilder();
                TimePeriodEntity timePeriodEntity = timePeriodConvertor.getEntity(timeBuilder.mergeFrom(entity.array()));
                timePeriodDao.save(timePeriodEntity);
            }else if (entityType == RPCEntityEnum.GIFT_ENTITY){
                GiftProto.GiftRequest.Builder giftBuilder = GiftProto.GiftRequest.newBuilder();
                GiftEntity giftEntity = giftConvertor.getEntity(giftBuilder.mergeFrom(entity.array()));
                giftEntity.setCode(Helper.getGiftChargeCode());
                giftDao.save(giftEntity);
            }

        } catch (Exception e) {
            throw new RemoteDaoException(e.getMessage());
        }

    }

    public void update(String id, ByteBuffer entity, RPCEntityEnum entityType) throws RemoteDaoException, TException {
        try{
            if (entityType == RPCEntityEnum.CATEGORY_ENTITY){
                CategoryProto.CategoryRequest.Builder catBuilder = CategoryProto.CategoryRequest.newBuilder();
                CategoryEntity categoryEntity = categoryConvertor.toEntity(catBuilder.mergeFrom(entity.array()));
                categoryDao.update(id, categoryEntity);
                cacheDao.clearCategoryCache();
            }else if (entityType == RPCEntityEnum.PACKAGE_ENTITY){
                PackageProto.PackageRequest.Builder packBuilder = PackageProto.PackageRequest.newBuilder();
                PackageEntity packageEntity = packageConvertor.getEntity(packBuilder.mergeFrom(entity.array()));
                packageDao.update(id, packageEntity);
                cacheDao.clearPackageCache();
            }else if (entityType == RPCEntityEnum.TIME_PERIOD_ENTITY){
                TimePeriodProto.TimePeriodRequest.Builder timeBuilder = TimePeriodProto.TimePeriodRequest.newBuilder();
                TimePeriodEntity timePeriodEntity = timePeriodConvertor.getEntity(timeBuilder.mergeFrom(entity.array()));
                timePeriodDao.update(id, timePeriodEntity);
            }else if (entityType == RPCEntityEnum.GIFT_ENTITY){
                GiftProto.GiftRequest.Builder giftBuilder = GiftProto.GiftRequest.newBuilder();
                GiftEntity giftEntity = giftConvertor.getEntity(giftBuilder.mergeFrom(entity.array()));
                giftDao.update(id, giftEntity);
            }
        }catch(Exception e){
            throw new RemoteDaoException(e.getMessage());
        }
    }

    public void remove(ByteBuffer entity, RPCEntityEnum entityType) throws RemoteDaoException, TException {

    }

    public void removeById(String id, RPCEntityEnum entityType) throws RemoteDaoException, TException {

    }

    public void fillTimePeriodList(ByteBuffer entity, RPCEntityEnum entityTpe) throws RemoteDaoException, TException {
        try{
            TimePeriodProto.TimePeriodFillRequest.Builder builder = TimePeriodProto.TimePeriodFillRequest.newBuilder();
            builder.mergeFrom(entity.array());

            for(int weekDay=0;weekDay<7;weekDay++){
                for (int j=builder.getFrom(); j<builder.getTo(); j=j+builder.getPeriod() ){
                    TimePeriodEntity timePeriodEntity = new TimePeriodEntity();
                    timePeriodEntity.setWeekDay(weekDay);
                    timePeriodEntity.setFromTime(j);
                    timePeriodEntity.setToTime(j+builder.getPeriod());
                    timePeriodDao.save(timePeriodEntity);
                }
            }
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }
    }

    public ByteBuffer getAll(RPCEntityEnum entityType) throws RemoteDaoException, TException {
        try{
            if (entityType == RPCEntityEnum.GIFT_ENTITY){
                List<GiftEntity> giftEntityList = giftDao.getAll();
                GiftProto.GiftList.Builder giftListBuilder = giftConvertor.getBuilderList(giftEntityList);
                return ((giftListBuilder == null)?null:ByteBuffer.wrap(giftListBuilder.build().toByteArray()));
            }
            return null;
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }
    }

    public ByteBuffer getById(String id, RPCEntityEnum entityType) throws RemoteDaoException, TException {

        try{
            return null;
        }catch (Exception e){
            throw new RemoteDaoException(e.getMessage());
        }

    }

}
