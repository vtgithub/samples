package ir.ord.application.dal.dao;


import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.PackageEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 7/12/17.
 */
public class CacheDaoRedisImpl implements CacheDao {
    public List<CategoryEntity> getCategoryEntityList() {
        return null;
    }

    public List<PackageEntity> getPackageEntityList() {
        return null;
    }

    public CategoryEntity getCategoryById(String id) {
        return null;
    }

    public PackageEntity getPackageById(String id) {
        return null;
    }

    public void initCache() {

    }

    public void initCategoryCache() throws DaoException {

    }

    public void initPackageCache() throws DaoException {

    }

    public void clearCategoryCache() {

    }

    public void clearPackageCache() {

    }

    public List<PackageEntity> getPackageListByCatIdSet(Set<String> leafNodeIdSet) {
        return null;
    }

    public List<PackageEntity> getAllButtonPackages() {
        return null;
    }
}
