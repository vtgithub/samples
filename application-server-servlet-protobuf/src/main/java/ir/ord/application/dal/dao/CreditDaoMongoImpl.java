package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.CreditEntity;
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
public class CreditDaoMongoImpl extends DaoMongoImpl<CreditEntity> implements CreditDao {

    public List<CreditEntity> getListByAccountId(String accountId) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("CreditEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("accountId", accountId);
            List<CreditEntity> creditEntityList = new ArrayList<CreditEntity>();
            FindIterable<Document> docList = collection.find(basicDBObject);
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                creditEntityList.add(
                        (CreditEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        ));
            }
            return creditEntityList;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    public CreditEntity getByAccountIdAndBankToken(String accountId, String bankToken) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("CreditEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("accountId", accountId);
            basicDBObject.put("bankInfoObject.token",bankToken);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            CreditEntity creditEntity = (CreditEntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return creditEntity;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    public CreditEntity getByAccountIdAndOrderId(String accountId, String orderId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("CreditEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("accountId", accountId);
            basicDBObject.put("orderId",orderId);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            CreditEntity creditEntity = (CreditEntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return creditEntity;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }


    public Double getBalanceSum(String accountId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("CreditEntity");
            BasicDBObject match = new BasicDBObject();
            match.put("$match", new BasicDBObject("accountId", accountId));
            BasicDBObject group = new BasicDBObject();
            group.put("$group", new BasicDBObject("_id", null).append("total", new BasicDBObject( "$sum", "$amount" )));
            List<BasicDBObject> aggregationList = new ArrayList<BasicDBObject>();
            aggregationList.add(match);
            aggregationList.add(group);
            Document resultDoc = collection.aggregate(aggregationList).first();
            return (resultDoc == null)? null :(Double.parseDouble(String.valueOf(resultDoc.get("total"))));

        }catch (Exception e){
            throw new DaoException(e);
        }
    }


}
