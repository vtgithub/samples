package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.BankPaymentEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class BankPaymentDaoMongoImpl extends DaoMongoImpl<BankPaymentEntity> implements BankPaymentDao{

    public BankPaymentEntity getByOrderId(Long orderId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("BankPaymentEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("orderId", orderId);
            Document docResult = collection.find(queryObject).first();
            if (docResult == null)
                return null;
            BankPaymentEntity bankPaymentEntity = (BankPaymentEntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return bankPaymentEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
