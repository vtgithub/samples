package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.ButtonActivationEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */
@ApplicationScoped
@Transactional
public class ButtonActivationDaoMongoImpl extends DaoMongoImpl<ButtonActivationEntity> implements ButtonActivationDao {

    public ButtonActivationEntity get(String deviceToken, String activationCode, Boolean isUsed) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("ButtonActivationEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("deviceToken", deviceToken);
            queryObject.put("activationCode", activationCode);
            queryObject.put("used", isUsed);
            Document docResult = collection.find(queryObject).first();
            if (docResult == null)
                return null;
            Object resultObject = DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return (ButtonActivationEntity) resultObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
