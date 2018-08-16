package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ir.ord.application.accessories.DaoHelper;
import org.bson.Document;

/**
 * Created by vahid on 6/13/17.
 */
public class PublicMongoDao {
    protected static MongoDatabase db = DaoHelper.getMongoDb();

    public static void deleteById(String entityName, String id){
        MongoCollection<Document> collection = db.getCollection(entityName);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("_id", id);
        collection.findOneAndDelete(basicDBObject);
    }
    public static void update(String entityName, String id, String entityJson){
        MongoCollection<Document> collection = db.getCollection(entityName);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("_id", id);
        collection.updateOne(
                basicDBObject,
                new Document("$set", Document.parse(entityJson))
        );
    }

    public static void insert(String entityName, String jsonStr) {
        MongoCollection<Document> collection = db.getCollection(entityName);
        collection.insertOne(Document.parse(jsonStr));
    }
}
