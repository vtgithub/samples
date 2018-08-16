package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.PackageEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */
@ApplicationScoped
@Transactional
public class PackageDaoMongoImpl extends DaoMongoImpl<PackageEntity> implements PackageDao {

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private CacheDao cacheDao;
//    public List<PackageEntity> getListByCategoryId(String categoryId) throws DaoException {
//        try{
//            MongoCollection<Document> collection = db.getCollection("PackageEntity");
//            BasicDBObject queryObject = new BasicDBObject();
//            queryObject.put("categoryId", categoryId);
//            FindIterable<Document> docList= collection.find(queryObject);
//            List<PackageEntity> resultList = new ArrayList<PackageEntity>();
//            MongoCursor<Document> iterator = docList.iterator();
//            while (iterator.hasNext()){
//                resultList.add(
//                        (PackageEntity) DaoHelper.getObjectFromJson(
//                                iterator.next().toJson(),
//                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
//                        )
//                );
//            }
//            return resultList;
//        }catch (Exception e){
//            
//            throw new DaoException(e);
//        }
//        return null;
//    }

    public List<PackageEntity> getAllButtonPackages() throws DaoException {
        try{
            List<PackageEntity> packageEntityList = cacheDao.getAllButtonPackages();
            if (packageEntityList != null)
                return packageEntityList;
            MongoCollection<Document> collection = db.getCollection("PackageEntity");
            BasicDBObject queryObjet = new BasicDBObject();
            queryObjet.put("categoryId", "1");
            FindIterable<Document> docResult = collection.find(queryObjet);
            packageEntityList = new ArrayList<PackageEntity>();
            MongoCursor<Document> iterator = docResult.iterator();
            while (iterator.hasNext()){
                packageEntityList.add(
                        (PackageEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return packageEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }


    public List<PackageEntity> getPackageListByParentCatId(String catId) throws DaoException {
        try{
            CategoryEntity categoryEntity = categoryDao.findNonButtonById(catId);
            Set<String> leafNodeIdSet = categoryDao.getLeafNodeIds(categoryEntity);
            List<PackageEntity> packageEntityList = cacheDao.getPackageListByCatIdSet(leafNodeIdSet);
            if (packageEntityList != null)
                return packageEntityList;
            MongoCollection<Document> collection = db.getCollection("PackageEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("categoryId", new BasicDBObject("$in", leafNodeIdSet));
            FindIterable<Document> docResult = collection.find(queryObject);
            MongoCursor<Document> iterator = docResult.iterator();
            packageEntityList = new ArrayList<PackageEntity>();
            while (iterator.hasNext()){
                packageEntityList.add(
                        (PackageEntity) DaoHelper.getObjectFromJson(
                                iterator.next().toJson(),
                                DaoHelper.getEntityClass(this.getClass().getSuperclass())
                        )
                );
            }
            return packageEntityList;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }




}
