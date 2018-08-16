package ir.ord.application.dal.dao;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.PackageEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by vahid on 7/12/17.
 */
@ApplicationScoped
@Transactional
public class CacheDaoJVMHeapImpl implements CacheDao {

    @Inject
    private CategoryDao categoryDao;
    @Inject
    private PackageDao packageDao;

    private Map<String, Map<String, Object>> cacheMap;

    public List<CategoryEntity> getCategoryEntityList() {
        Map<String, Object> categoryMap = cacheMap.get("CategoryMap");
        List<CategoryEntity> categoryEntityList =  (List<CategoryEntity>)(Object)getValueListFromMap(categoryMap);
        return categoryEntityList;
    }

    public List<PackageEntity> getPackageEntityList() {
        Map<String, Object> packageMap = cacheMap.get("PackageMap");
        List<PackageEntity> packageEntityList =  (List<PackageEntity>)(Object)getValueListFromMap(packageMap);
        return packageEntityList;
    }

    public CategoryEntity getCategoryById(String id) {
        Map<String, Object> categoryMap = cacheMap.get("CategoryMap");
        return (CategoryEntity) categoryMap.get(id);
    }

    public PackageEntity getPackageById(String id) {
        Map<String, Object> packageMap = cacheMap.get("PackageMap");
        return (PackageEntity) packageMap.get(id);
    }

    public void initCache() throws DaoException {
        cacheMap = DaoHelper.getCacheMap();
        cacheMap = new HashMap<String, Map<String, Object>>();
        initCategoryCache();
        initPackageCache();
    }

    public void initCategoryCache() throws DaoException {
        cacheMap.put("CategoryMap", new HashMap<String, Object>());
        List<CategoryEntity> allCategories = categoryDao.getAllForCache();
        Map<String, Object> categoryMap = cacheMap.get("CategoryMap");
        for (CategoryEntity categoryEntity : allCategories) {
            categoryMap.put(categoryEntity.get_id(), categoryEntity);
        }
        System.out.println("categoryMap size:___________"+cacheMap.get("CategoryMap").size());
    }

    public void initPackageCache() throws DaoException {
        cacheMap.put("PackageMap", new HashMap<String, Object>());
        List<PackageEntity> allPackages = packageDao.getAllForCache();
        Map<String, Object> packageMap = cacheMap.get("PackageMap");
        for (PackageEntity packageEntity : allPackages) {
            packageMap.put(packageEntity.get_id(), packageEntity);
        }
        System.out.println("packageMap size:___________"+cacheMap.get("PackageMap").size());
    }

    public void clearCategoryCache() {
        Map<String, Object> categoryMap = cacheMap.get("CategoryMap");
        categoryMap.clear();
    }


    public void clearPackageCache() {
        Map<String, Object> packageMap = cacheMap.get("PackageMap");
        packageMap.clear();
    }


    public List<PackageEntity> getAllButtonPackages() {
//        List<PackageEntity> packageEntityList = getPackageEntityList();
//        if (packageEntityList == null)
//            return null;
//        List<PackageEntity> outputPackageEntityList = new ArrayList<PackageEntity>();
//        for (PackageEntity packageEntity : packageEntityList) {
//            if (packageEntity.getCategoryId().equals("1")){
//                outputPackageEntityList.add(packageEntity);
//            }
//        }
//        return outputPackageEntityList;
        //todo uncomment
        return null;
    }

    public List<PackageEntity> getPackageListByCatIdSet(Set<String> categoryIdSet) {

//        List<PackageEntity> packageEntityList = getPackageEntityList();
//        if (categoryIdSet == null || packageEntityList == null)
//            return null;
//        List<PackageEntity> outputPackageEntity = new ArrayList<PackageEntity>();
//        for (PackageEntity packageEntity : packageEntityList) {
//            if (categoryIdSet.contains(packageEntity.getCategoryId()))
//                outputPackageEntity.add(packageEntity);
//        }
//        return outputPackageEntity;
        //todo uncomment
        return null;
    }
    //------------- Helpers


    private List<Object> getValueListFromMap(Map<String, Object> map) {
        if (map == null)
            return null;
        List<Object> objectList = new ArrayList<Object>();
        for(Map.Entry<String, Object>  entry: map.entrySet()){
            objectList.add(entry.getValue());
        }
        return objectList;
    }
}
