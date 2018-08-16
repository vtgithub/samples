package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.CommodityState;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.MessageEntity;
import ir.ord.application.dal.entities.OrderEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 11/26/17.
 */
@ApplicationScoped
@Transactional
public class MessageDaoMongoImpl extends DaoMongoImpl<MessageEntity> implements MessageDao {
    public List<MessageEntity> getCurrentMessage(Long currentTime) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("MessageEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("startTime", new BasicDBObject("$lt", currentTime));
            queryObject.put("endTime", new BasicDBObject("$gt", currentTime));
            FindIterable<Document> docList = collection.find(queryObject);
            if (docList == null )
                return null;
            List<MessageEntity> messageEntityList = new ArrayList<MessageEntity>();
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                messageEntityList.add(
                        (MessageEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return messageEntityList;

        }catch (Exception e){
            throw new DaoException(e);
        }

    }
}
