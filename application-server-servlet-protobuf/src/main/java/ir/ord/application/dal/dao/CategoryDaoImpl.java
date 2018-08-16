package ir.ord.application.dal.dao;


import ir.ord.application.dal.entities.CategoryEntity;

import java.util.Set;

/**
 * Created by vahid on 4/22/17.
 */

public class CategoryDaoImpl extends DaoImpl<CategoryEntity> implements CategoryDao {

    public CategoryEntity getNonButton() throws DaoException {
        return null;
    }

    public CategoryEntity getButton() throws DaoException {
        return null;
    }

    public CategoryEntity findNonButtonById(String catId) throws DaoException {
        return null;
    }

    public Set<String> getLeafNodeIds(CategoryEntity categoryEntity) {
        return null;
    }
}
