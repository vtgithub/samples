package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.ProductConverter;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.ProductDao;
import ir.ord.application.dal.entities.ProductEntity;
import ir.ord.application.dto.ProductDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 12/23/17.
 */
@Stateless
public class ProductBiz {
    @Inject
    private ProductDao productDao;
    @Inject
    private ProductConverter productConverter;

    public List<ProductDto> getProductListByCatId(String catId) throws CustomValidationException, DaoException {
        if (catId.trim() == null || catId.trim().equals(""))
            throw new CustomValidationException(ValidationMessages.catIdEmpty);
        List<ProductEntity> productEntityList = productDao.getListByCatId(catId);
        List<ProductDto> productDtoList = productConverter.getDtoList(productEntityList);
        return productDtoList;
    }
}
