package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.dal.entities.CountersEntity;
import org.bson.Document;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/22/17.
 */
@Transactional
@ApplicationScoped
public class CountersDaoMongoImpl extends DaoMongoImpl<CountersEntity> implements CountersDao{
    public Long getNextSequence(String counterName) {
        MongoCollection<Document> collection = db.getCollection("CountersEntity");
        BasicDBObject findObj = new BasicDBObject();
        findObj.put("_id", counterName);
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$inc", new BasicDBObject("seq", 1));
        Document resultDoc = collection.findOneAndUpdate(findObj, updateObj);
        Double counter = (Double) resultDoc.get("seq");
        System.out.println(counter.longValue());
        return counter.longValue();
    }
}
