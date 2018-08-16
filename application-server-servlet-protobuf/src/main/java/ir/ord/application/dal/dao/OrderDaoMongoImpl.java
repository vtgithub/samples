package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.CommodityState;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.OrderEntity;
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
public class OrderDaoMongoImpl extends DaoMongoImpl<OrderEntity> implements OrderDao {

    public List<OrderEntity> getListByAccountId(String accountId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("OrderEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("accountId", accountId);
            FindIterable<Document> docList = collection.find(queryObject);
            if (docList == null )
                return null;
            List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                orderEntityList.add(
                        (OrderEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return orderEntityList;

        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public long getOrderCountByAccountId(String accountId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("OrderEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("accountId", accountId);
            long count = collection.count(queryObject);
            return count;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<OrderEntity> getExpiredPaiedOrders() throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("OrderEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("currentStatus.state", CommodityState.PAID.getCode());
            queryObject.put("currentStatus.timeStamp", new BasicDBObject("$lt", DaoHelper.getCurrentTime() - 15*60000));
            FindIterable<Document> docList = collection.find(queryObject);
            if (docList == null )
                return null;
            List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                orderEntityList.add(
                        (OrderEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return orderEntityList;

        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<OrderEntity> getWarehousePendingList() throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("OrderEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("currentStatus.state", CommodityState.WAREHOUSE_PENDING.getCode());
            FindIterable<Document> docList = collection.find(queryObject);
            if (docList == null )
                return null;
            List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                orderEntityList.add(
                        (OrderEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return orderEntityList;

        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

}
