package ir.ord.application.dal.dao;


import ir.ord.application.dal.entities.PackageEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */
public interface PackageDao extends Dao<PackageEntity> {
//    List<PackageEntity> getListByCategoryId(String categoryId) throws DaoException;

    List<PackageEntity> getAllButtonPackages() throws DaoException;

    List<PackageEntity> getPackageListByParentCatId(String catId) throws DaoException;


}
