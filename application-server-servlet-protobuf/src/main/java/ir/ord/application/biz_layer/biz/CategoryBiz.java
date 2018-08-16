package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.CategoryConvertor;

import ir.ord.application.dal.dao.CategoryDao;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dto.CategoryDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@ApplicationScoped
public class CategoryBiz {

    @Inject
    private CategoryConvertor categoryConvertor;

    @Inject
    private CategoryDao categoryDao;


    public List<CategoryDto> getCategoryList() throws DaoException {
        List<CategoryEntity> categoryEntityList = categoryDao.getAll();
        List<CategoryDto> categoryDtoList = categoryConvertor.getCategoryDtoList(categoryEntityList);
        return categoryDtoList;
    }

    public CategoryDto getNonButtonCategory() throws DaoException {
        CategoryEntity categoryEntity= categoryDao.getNonButton();
        CategoryDto categoryDto = categoryConvertor.toDto(categoryEntity);
        return categoryDto;
    }


    public CategoryDto getButtonCategoryList() throws DaoException {
        CategoryEntity categoryEntity = categoryDao.getButton();
        CategoryDto categoryDto = categoryConvertor.toDto(categoryEntity);
        return categoryDto;

    }

    public void initCategoryCache() throws DaoException, IOException {
//        List<CategoryEntity> allCategoryEntities = categoryDao.getAllForCache();
//        try{
//            for (CategoryEntity catEntity: allCategoryEntities) {
//                DaoHelper.getRedisDb().hset(
//                        "CategoryHash".getBytes(),
//                        catEntity.get_id().getBytes(),
//                        Helper.BytesUtil.toByteArray(catEntity)
//                );
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new DaoException();
//        }
    }
}
