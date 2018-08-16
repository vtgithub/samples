package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.TimePeriodEntity;
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
public class TimePeriodDaoMongoImpl extends DaoMongoImpl<TimePeriodEntity> implements TimePeriodDao {

    public List<TimePeriodEntity> getCustomTimePeriodList(int fromWeekDay, int toWeekDay) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("TimePeriodEntity");
            List<TimePeriodEntity> resultList = new ArrayList<TimePeriodEntity>();
            if (fromWeekDay <= toWeekDay) {
                BasicDBObject query = new BasicDBObject();
                query.put("weekDay", new BasicDBObject("$gte", fromWeekDay).append("$lte", toWeekDay));
                FindIterable<Document> docList = collection.find(query);
                if (docList == null) {
                    return null;
                }
                MongoCursor<Document> iterator = docList.iterator();
                while (iterator.hasNext()){
                    resultList.add(
                            (TimePeriodEntity) DaoHelper.getObjectFromJson(
                                    iterator.next().toJson(),
                                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
                            )
                    );
                }
            }else {
                BasicDBObject query = new BasicDBObject();
                query.put("weekDay", new BasicDBObject("$gte", fromWeekDay).append("$lte", 6));
                FindIterable<Document> docList = collection.find(query);
                BasicDBObject query2 = new BasicDBObject();
                query2.put("weekDay", new BasicDBObject("$gte", 0).append("$lte", toWeekDay));
                FindIterable<Document> docList2 = collection.find(query2);
                if (docList == null && docList2 == null) {
                    return null;
                }
                MongoCursor<Document> iterator = docList.iterator();
                while (iterator.hasNext()){
                    resultList.add(
                            (TimePeriodEntity) DaoHelper.getObjectFromJson(
                                    iterator.next().toJson(),
                                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
                            )
                    );
                }
                iterator = docList2.iterator();
                while (iterator.hasNext()){
                    resultList.add(
                            (TimePeriodEntity) DaoHelper.getObjectFromJson(
                                    iterator.next().toJson(),
                                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
                            )
                    );
                }
            }
            return resultList;
        }catch (Exception e){

            throw new DaoException(e);
        }
    }
}
