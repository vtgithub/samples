package ir.ord.application.dal.dao;

import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dal.entities.PackageEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 7/12/17.
 */
public interface CacheDao {
    List<CategoryEntity> getCategoryEntityList();
    List<PackageEntity> getPackageEntityList();
    CategoryEntity getCategoryById(String id);
    PackageEntity getPackageById(String id);
    void initCache() throws DaoException;
    void initCategoryCache() throws DaoException;
    void initPackageCache() throws DaoException;
    void clearCategoryCache();
    void clearPackageCache();
    List<PackageEntity> getPackageListByCatIdSet(Set<String> leafNodeIdSet);
    List<PackageEntity> getAllButtonPackages();
}
