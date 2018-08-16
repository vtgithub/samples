package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.QREntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class QRDaoMongoImpl extends DaoMongoImpl<QREntity> implements QRDao{

    public QREntity getBySessionId(String sessionId, Boolean used, Long currentMinusTTL) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("QREntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("sessionId", sessionId);
            queryObject.put("used", used);
            queryObject.put("creationTime", new BasicDBObject("$gt", currentMinusTTL));
            Document docResult = collection.find(queryObject).first();
            if (docResult == null)
                return null;
            QREntity qrEntity = (QREntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return qrEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public QREntity getByEncryptedCode(String base64Encrypted, Boolean used, Long creationTime) throws DaoException {
        try {
            MongoCollection<Document> collection = db.getCollection("QREntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("encryptedCode", base64Encrypted);
            queryObject.put("used", used);
            queryObject.put("creationTime", new BasicDBObject("$gt", creationTime));
            Document docResult = collection.find(queryObject).first();
            if (docResult == null)
                return null;
            QREntity qrEntity = (QREntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return qrEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }
}
