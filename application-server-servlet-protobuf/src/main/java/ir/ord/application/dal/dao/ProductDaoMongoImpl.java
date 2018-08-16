package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.ProductEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/23/17.
 */
@Transactional
@ApplicationScoped
public class ProductDaoMongoImpl extends DaoMongoImpl<ProductEntity> implements ProductDao{
    public List<ProductEntity> getListByCatId(String catId) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("ProductEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("catId", catId);
            List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
            FindIterable<Document> documents = collection.find(basicDBObject);
            MongoCursor<Document> iterator = documents.iterator();
            if (documents == null || iterator == null )
                return null;
            while (iterator.hasNext()){
                productEntityList.add(
                        (ProductEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return productEntityList;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }
}
