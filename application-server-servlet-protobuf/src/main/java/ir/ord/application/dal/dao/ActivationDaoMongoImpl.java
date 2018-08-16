package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.ActivationEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
@ApplicationScoped
@Transactional
public class ActivationDaoMongoImpl extends DaoMongoImpl<ActivationEntity> implements ActivationDao {

    public ActivationEntity get(String activationCode, String deviceId, Boolean isUsed) throws DaoException {

        try{
            ActivationEntity activationEntity = getFromActivationWhiteList(activationCode, deviceId, isUsed);
            if (activationEntity != null){
                activationEntity.setCreationTime(DaoHelper.getCurrentTime());
                return activationEntity;
            }
            MongoCollection<Document> collection = db.getCollection("ActivationEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("deviceId", deviceId);
            basicDBObject.put("activationCode", activationCode);
            basicDBObject.put("used", isUsed);
            // expire if 30 minutes elapse
            basicDBObject.put("creationTime", new BasicDBObject(
                    "$gt", DaoHelper.getCurrentTime() - DaoHelper.getActivationCodeExpirationMillies())
            );

            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            Object activationEntityObject = DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return (ActivationEntity) activationEntityObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public Long getDailyUnusedActivationCodeCount(String deviceId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("ActivationEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("deviceId", deviceId);
            basicDBObject.put("used", false);
            // expire if 30 minutes elapse
            basicDBObject.put("creationTime", new BasicDBObject(
                    "$gt", DaoHelper.getCurrentTime() - DaoHelper.getOneDayMillies())
            );

            long count = collection.count(basicDBObject);

            return count;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
//        return null;
    }


    private ActivationEntity getFromActivationWhiteList(String activationCode, String deviceId, boolean isUsed) {
        List<ActivationEntity> activationWhiteList = DaoHelper.getActivationWhiteList();
        for (ActivationEntity activationEntity : activationWhiteList) {
            if (activationEntity.getDeviceId().equals(deviceId.trim()) && activationEntity.getActivationCode().equals(activationCode.trim()) && activationEntity.getUsed() == isUsed)
                return activationEntity;
        }
        return null;
    }

}
