package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.PackageEntity;
import org.bson.Document;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 4/30/17.
 */
abstract class DaoMongoImpl<E> implements Dao<E> {
    protected MongoDatabase db = DaoHelper.getMongoDb();

    @Inject
    private CacheDao cacheDao;

    public void save(E entity) throws DaoException {
        try {
            DaoHelper.isEntityValid(entity);
            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);
            String json=DaoHelper.getEntityJson(entity);
            collection.insertOne(Document.parse(json));
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public void update(String id, E newEntity) throws DaoException {
        try {
            DaoHelper.isEntityValid(newEntity);
            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);

            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("_id", id);
            collection.updateOne(
                    basicDBObject,
                    new Document("$set", Document.parse(DaoHelper.getEntityJson(newEntity)))
            );
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public void removeById(String id) throws DaoException {
        try {
            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("_id", id);
            collection.findOneAndDelete(basicDBObject);
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public void remove(E entity) throws DaoException {
        try{
            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);
            collection.findOneAndDelete(
                    Document.parse(DaoHelper.getEntityJson(entity))
            );

        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<E> getAll() throws DaoException {
        try{
            String className = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            List<E> entityList = getAllFromCache(className);
            if (entityList != null && entityList.size() != 0){
                System.out.println("V_______ get info from cache ______V");
                return entityList;
            }
            MongoCollection<Document> collection = db.getCollection(className);
            FindIterable<Document> docList = collection.find();
            List<Object> resultList = new ArrayList<Object>();
            if (docList == null)
                return null;
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                resultList.add(
                        DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return (List<E>) resultList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public List<E> getAllForCache() throws DaoException {
        try{
            String entityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(entityClassName );
            FindIterable<Document> docList = collection.find();
            List<Object> resultList = new ArrayList<Object>();
            if (resultList == null)
                return null;
            MongoCursor<Document> iterator = docList.iterator();
            while (iterator.hasNext()){
                resultList.add(
                        DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return (List<E>) resultList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public E getById(String id) throws DaoException {
        try{
            // get from cache if exists
            E entity = getByIdFromCache(id, DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass()));
            if (entity != null)
                return entity;

            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("_id", id);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            Object resultObject = DaoHelper.getObjectFromJson(docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass()));
            return (E) resultObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    private E getByIdFromCache(String id, String mongoEntityClassName){
        if (mongoEntityClassName.trim().equals("CategoryEntity")) {
            try {
                CategoryEntity categoryEntity = cacheDao.getCategoryById(id);
                if (categoryEntity == null)
                    cacheDao.initCategoryCache();
//                E entity = (E) DaoHelper.BytesUtil.toObject(DaoHelper.getRedisDb().hget("CategoryHash".getBytes(), id.getBytes()));
                return (E) categoryEntity;
            } catch (Exception e) {
                
                return null;
            }
        }else if (mongoEntityClassName.trim().equals("PackageEntity")){
            try {
                PackageEntity packageEntity = cacheDao.getPackageById(id);
                if (packageEntity == null)
                    cacheDao.initPackageCache();
//                E entity = (E) DaoHelper.BytesUtil.toObject(DaoHelper.getRedisDb().hget("PackageHash".getBytes(), id.getBytes()));
                return (E) packageEntity;
            } catch (Exception e) {
                
                return null;
            }
        }
        return null;
    }


    private List<E> getAllFromCache(String className) {
        if (className == null)
            return null;
        try {
            List<E> entityList = null;
            if (className.trim().equals("CategoryEntity")){
                List<CategoryEntity> categoryEntityList = cacheDao.getCategoryEntityList();
                if (categoryEntityList == null || categoryEntityList.size() == 0)
                    cacheDao.initCategoryCache();
//                Map<byte[], byte[]> categoryHash = DaoHelper.getRedisDb().hgetAll("CategoryHash".getBytes());
//                entityList = (List<E>) DaoHelper.getObjectListFromByteMap(categoryHash);
                return (List<E>) categoryEntityList;

            }else if (className.trim().equals("PackageEntity")){
                List<PackageEntity> packageEntityList = cacheDao.getPackageEntityList();
                if (packageEntityList == null || packageEntityList.size() == 0)
                    cacheDao.initPackageCache();
//                Map<byte[], byte[]> packageHash = DaoHelper.getRedisDb().hgetAll("PackageHash".getBytes());
//                entityList = (List<E>) DaoHelper.getObjectListFromByteMap(packageHash);
                return (List<E>) packageEntityList;
            }
            return entityList;
        }catch (Exception e){
            
            return null;
        }

    }


    public void dropEntity() throws DaoException {
        try{
            String mongoEntityClassName = DaoHelper.getMongoEntityClassName(this.getClass().getSuperclass());
            MongoCollection<Document> collection = db.getCollection(mongoEntityClassName);
            collection.drop();
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }
}
