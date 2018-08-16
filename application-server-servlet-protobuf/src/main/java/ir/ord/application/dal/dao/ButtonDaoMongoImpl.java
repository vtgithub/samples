package ir.ord.application.dal.dao;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.ButtonEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
@ApplicationScoped
@Transactional
public class ButtonDaoMongoImpl extends DaoMongoImpl<ButtonEntity> implements ButtonDao {

    public ButtonEntity getByDeviceToken(String deviceToken) throws DaoException {
        try{
            MongoCollection<Document> document = db.getCollection("ButtonEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("deviceToken", deviceToken);
            Document docResult = document.find(queryObject).first();
            if (docResult == null)
                return null;
            Class<?> superclass = this.getClass().getSuperclass();
            Object resultObject = DaoHelper.getObjectFromJson(docResult.toJson(),
                    DaoHelper.getEntityClass(superclass)
            );

            return (ButtonEntity) resultObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<ButtonEntity> getListByAccountId(String accountId) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("ButtonEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("accountId",accountId);
            FindIterable<Document> docResult = collection.find(queryObject);
            if (docResult == null)
                return null;
            List<ButtonEntity> buttonEntityList = new ArrayList<ButtonEntity>();
            MongoCursor<Document> iterator = docResult.iterator();
            while (iterator.hasNext()){
                buttonEntityList.add(
                        (ButtonEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return buttonEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<ButtonEntity> getListByAccountAndAddressId(String accountId, String addressID) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("ButtonEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("accountId",accountId);
            queryObject.put("addressId",addressID);
            FindIterable<Document> docList = collection.find(queryObject);
            if (docList == null)
                return null;
            List<ButtonEntity> buttonEntityList = new ArrayList<ButtonEntity>();
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                buttonEntityList.add(
                        (ButtonEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return buttonEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
