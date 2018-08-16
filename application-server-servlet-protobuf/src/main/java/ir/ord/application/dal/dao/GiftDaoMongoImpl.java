package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.GiftEntity;
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
public class GiftDaoMongoImpl extends DaoMongoImpl<GiftEntity> implements GiftDao {

    public GiftEntity getUnused(String code) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("GiftEntity");
            BasicDBObject queryObject =  new BasicDBObject();
            queryObject.put("code", code);
//            queryObject.put("accountId", accountId);
            queryObject.put("usedTime", new BasicDBObject("$exists",false));
            Document docResult = collection.find(queryObject).first();
            if (docResult == null)
                return null;
            Object resultObject = DaoHelper.getObjectFromJson(docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return (GiftEntity) resultObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public GiftEntity getByCode(String code) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("GiftEntity");
            BasicDBObject queryObject =  new BasicDBObject();
            queryObject.put("code", code);
            Document docResult = collection.find(queryObject).first();
            if (docResult == null )
                return null;
            Object resultObject = DaoHelper.getObjectFromJson(docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return (GiftEntity) resultObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<GiftEntity> getByAccountId(String accountId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("GiftEntity");
            BasicDBObject queryObject =  new BasicDBObject();
            queryObject.put("userId", accountId);
            FindIterable<Document> documents = collection.find(queryObject);
            if (documents == null)
                return null;
            MongoCursor<Document> iterator = documents.iterator();
            List<GiftEntity> giftEntityList = new ArrayList<GiftEntity>();
            while (iterator.hasNext()){
                giftEntityList.add(
                        (GiftEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return giftEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
