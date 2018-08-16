package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.UnpaiedEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class UnpaiedDaoMongoImpl extends DaoMongoImpl<UnpaiedEntity> implements UnpaiedDao{

    public List<UnpaiedEntity> getByAccountId(String accountId) {
        MongoCollection<Document> collection = db.getCollection("UnpaiedEntity");
        BasicDBObject queryObject = new BasicDBObject();
        queryObject.put("accountId", accountId);
        FindIterable<Document> documents = collection.find(queryObject);
        if (documents == null)
            return null;
        List<UnpaiedEntity> unpaiedEntityList = new ArrayList<UnpaiedEntity>();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()){
            UnpaiedEntity unpaiedEntity = (UnpaiedEntity) DaoHelper.getObjectFromJson(
                    iterator.next().toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            unpaiedEntityList.add(unpaiedEntity);
        }
        return unpaiedEntityList;
    }

    public void removeByOrderId(String orderId) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("UnpaiedEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("orderId", orderId);
            collection.findOneAndDelete(basicDBObject);
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public UnpaiedEntity getByOrderId(String orderId) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("UnpaiedEntity");
            BasicDBObject query = new BasicDBObject();
            query.put("orderId", orderId);
            Document docResult = collection.find(query).first();
            if (docResult == null)
                return null;
            UnpaiedEntity result = (UnpaiedEntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return result;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
