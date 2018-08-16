package ir.ord.application.dal.dao;


import ir.ord.application.dal.entities.CategoryEntity;

import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */
public interface CategoryDao extends Dao<CategoryEntity> {
    CategoryEntity getNonButton() throws DaoException;
    CategoryEntity getButton() throws DaoException;
    CategoryEntity findNonButtonById(String catId) throws DaoException;
    Set<String> getLeafNodeIds(CategoryEntity categoryEntity);
}
