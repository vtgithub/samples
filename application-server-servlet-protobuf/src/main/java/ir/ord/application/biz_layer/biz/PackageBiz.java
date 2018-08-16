package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.PackageConvertor;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.PackageDao;
import ir.ord.application.dal.entities.PackageEntity;
import ir.ord.application.dto.PackageDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/7/17.
 */
@Stateless
public class PackageBiz {

    @Inject
    private PackageDao packageDao;
    @Inject
    private PackageConvertor packageConvertor;

    public List<PackageDto> getButtonPackageList() throws DaoException {
        List<PackageEntity> packageEntityList = packageDao.getAllButtonPackages();
        List<PackageDto> packageDtoList = packageConvertor.getPackageDtoList(packageEntityList);
        return packageDtoList;
    }



    public List<PackageDto> getPackageListByCatId(String catId) throws DaoException {
        List<PackageEntity> packageEntityList = packageDao.getPackageListByParentCatId(catId); //cat رب
        List<PackageDto> packageDtoList = packageConvertor.getPackageDtoList(packageEntityList);
        return packageDtoList;
    }

    public List<PackageDto> getPackageList() throws DaoException {
        List<PackageEntity> packageEntityList = packageDao.getAll();
        List<PackageDto> packageDtoList = packageConvertor.getPackageDtoList(packageEntityList);
        return packageDtoList;
    }


    public void initPackageCache() throws DaoException, IOException {
//        List<PackageEntity> allPackageEntities = packageDao.getAllForCache();
//        for (PackageEntity packageEntity : allPackageEntities) {
//            DaoHelper.getRedisDb().hset(
//                    "PackageHash".getBytes(),
//                    packageEntity.get_id().getBytes(),
//                    Helper.BytesUtil.toByteArray(packageEntity)
//            );
//        }
    }
    //-------------- helper functions


}
