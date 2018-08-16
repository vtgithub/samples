package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.CategoryEntity;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */
@ApplicationScoped
@Transactional
public class CategoryDaoMongoImpl extends DaoMongoImpl<CategoryEntity> implements CategoryDao {


    @Inject
    private CacheDao cacheDao;

    public CategoryEntity getNonButton() throws DaoException {
        try{
            CategoryEntity categoryEntity = cacheDao.getCategoryById("2");
            if (categoryEntity != null)
                return categoryEntity;
            MongoCollection<Document> collection = db.getCollection("CategoryEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("_id",  "2");
            Document docResult =  collection.find(queryObject).first();
            if (docResult == null)
                return null;
            categoryEntity = (CategoryEntity) DaoHelper.getObjectFromJson(docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return categoryEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public CategoryEntity getButton() throws DaoException {
        try{
            CategoryEntity categoryEntity = cacheDao.getCategoryById("1");
            if (categoryEntity != null)
                return categoryEntity;
            MongoCollection<Document> collection = db.getCollection("CategoryEntity");
            BasicDBObject queryObject = new BasicDBObject();
            queryObject.put("_id",  "1");
            Document docResult =  collection.find(queryObject).first();
            if (docResult == null)
                return null;
            categoryEntity = (CategoryEntity) DaoHelper.getObjectFromJson(docResult.toJson(),
                    DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return categoryEntity;
        }catch (Exception e){
            
            throw new DaoException(e);
        }
    }

    public CategoryEntity findNonButtonById(String catId) throws DaoException {
        CategoryEntity nonButtonCategory = getNonButton();
        return recursiveFind(nonButtonCategory,catId);
    }


    public Set<String> getLeafNodeIds(CategoryEntity categoryEntity) {
        Set<String> leafNodeIdSet = new HashSet<String>();
        if (categoryEntity.getChildren() == null || categoryEntity.getChildren().size() == 0){
            leafNodeIdSet.add(categoryEntity.get_id());
        }else {
            for (CategoryEntity childCategoryEntity : categoryEntity.getChildren())
                leafNodeIdSet.addAll(getLeafNodeIds(childCategoryEntity));
        }
        return leafNodeIdSet;
    }


    //  --------------- helper functions
    private CategoryEntity recursiveFind(CategoryEntity nonButtonCategory, String catId) {
        if (nonButtonCategory == null)
            return null;
        if(catId.equals(nonButtonCategory.get_id()))
            return nonButtonCategory;
        CategoryEntity founded = null;
        if(nonButtonCategory.getChildren() != null) {
            for (CategoryEntity childCategoryEntity : nonButtonCategory.getChildren()) {
                founded = recursiveFind(childCategoryEntity, catId);
                if (founded != null)
                    break;
            }
        }
        return founded;
    }
}
