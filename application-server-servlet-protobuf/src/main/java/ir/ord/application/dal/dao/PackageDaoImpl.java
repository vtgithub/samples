package ir.ord.application.dal.dao;



import ir.ord.application.dal.entities.PackageEntity;

import java.util.List;

/**
 * Created by vahid on 4/22/17.
 */

public class PackageDaoImpl extends DaoImpl<PackageEntity> implements PackageDao {

    public List<PackageEntity> getListByCategoryId(String categoryId) throws DaoException {
            return null;
    }

    public List<PackageEntity> getAllButtonPackages() throws DaoException {
        return null;
    }

    public List<PackageEntity> getPackageListByParentCatId(String catId) throws DaoException {
        return null;
    }
}
