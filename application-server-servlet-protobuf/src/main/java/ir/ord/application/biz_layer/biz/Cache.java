package ir.ord.application.biz_layer.biz;

import ir.ord.application.dal.dao.CacheDao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by vahid on 7/15/17.
 */
@Stateless(mappedName = "CacheRemote")
@Remote
public class Cache implements CacheRemote {
    @Inject
    private CacheDao cacheDao;

    public void clearCache() {
    }

    public void clearPackageCache() {
        cacheDao.clearPackageCache();
    }

    public void clearCategoryCache() {
        cacheDao.clearCategoryCache();
    }

}
