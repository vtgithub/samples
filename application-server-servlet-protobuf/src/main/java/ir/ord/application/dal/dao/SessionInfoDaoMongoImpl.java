package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.DevicePushToken;
import ir.ord.application.dal.entities.SessionInfoEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class SessionInfoDaoMongoImpl extends DaoMongoImpl<SessionInfoEntity> implements SessionInfoDao {

    public List<SessionInfoEntity> get(String deviceId, String accountId) throws DaoException {

        try{
            MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("deviceId", deviceId);
            basicDBObject.put("accountId", accountId);
            basicDBObject.put("active", true);
            FindIterable<Document> docResult = collection.find(basicDBObject);
            if (docResult == null)
                return null;
            MongoCursor<Document> iterator = docResult.iterator();
            List<SessionInfoEntity> sessionInfoEntityList = new ArrayList<SessionInfoEntity>();
            while (iterator.hasNext()){
                sessionInfoEntityList.add(
                        (SessionInfoEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return sessionInfoEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public SessionInfoEntity getActiveSessionById(String sessionId) throws DaoException {

        try{
            MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("active", true);
            basicDBObject.put("_id",sessionId);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            Object sessionInfoEntityObject = DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return (SessionInfoEntity) sessionInfoEntityObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<SessionInfoEntity> getAllActiveAccountSessions(String accountId) throws DaoException {
       try{
           MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
           BasicDBObject basicDBObject = new BasicDBObject();
           basicDBObject.put("accountId", accountId);
           basicDBObject.put("active", true);
           FindIterable<Document> docResultList = collection.find(basicDBObject);
           if (docResultList == null)
               return null;
           List<SessionInfoEntity> sessionInfoEntityList = new ArrayList<SessionInfoEntity>();
           MongoCursor<Document> iterator = docResultList.iterator();
           while (iterator.hasNext()){
               sessionInfoEntityList.add(
                       (SessionInfoEntity) DaoHelper.getObjectFromJson(iterator.next().toJson(),
                               DaoHelper.getEntityClass(this.getClass().getSuperclass()))
               );
           }
           return sessionInfoEntityList;
       }catch (Exception e){
           throw new DaoException(e);
       }
    }

    public DevicePushToken getDevicePushToken(String accountId) throws DaoException {
        List<SessionInfoEntity> accountActiveSessionList = getAllActiveAccountSessions(accountId);
        if (accountActiveSessionList == null ||  accountActiveSessionList.size() == 0)
            return null;
        DevicePushToken devicePushToken = new DevicePushToken();
        Set<String> pushTokenSet = new HashSet<String>();
        for (SessionInfoEntity sessionInfoEntity : accountActiveSessionList) {
            if (sessionInfoEntity.getPushToken() != null)
                pushTokenSet.add(sessionInfoEntity.getPushToken());
        }
        devicePushToken.setPushTokenSet(pushTokenSet);
        devicePushToken.setPlatform(accountActiveSessionList.get(0).getPlatform());
        return devicePushToken;
    }

    public List<SessionInfoEntity> getActiveSessionsByDeviceId(String deviceId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("deviceId", deviceId);
            basicDBObject.put("active", true);
            FindIterable<Document> docResult = collection.find(basicDBObject);
            if (docResult == null)
                return null;
            MongoCursor<Document> iterator = docResult.iterator();
            List<SessionInfoEntity> sessionInfoEntityList = new ArrayList<SessionInfoEntity>();
            while (iterator.hasNext()){
                sessionInfoEntityList.add(
                        (SessionInfoEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return sessionInfoEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public SessionInfoEntity getBySecondaryId(String secondaryId) throws DaoException {

        try{
            MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("secondaryId", secondaryId);
            basicDBObject.put("active", true);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;

            SessionInfoEntity sessionInfoEntity = (SessionInfoEntity) DaoHelper.getObjectFromJson(
                    docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );

            return sessionInfoEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<SessionInfoEntity> getAllActiveSessions() throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("SessionInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("active", true);
            FindIterable<Document> docResult = collection.find(basicDBObject);
            if (docResult == null)
                return null;
            MongoCursor<Document> iterator = docResult.iterator();
            List<SessionInfoEntity> sessionInfoEntityList = new ArrayList<SessionInfoEntity>();
            while (iterator.hasNext()){
                sessionInfoEntityList.add(
                        (SessionInfoEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return sessionInfoEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }



}
