package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.ProductEntity;
import ir.ord.application.dto.ProductDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/23/17.
 */
@ApplicationScoped
public class ProductConverter {
    @Inject
    private NutritionFactConverter nutritionFactConverter;

    public ProductDto getDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setBarcodeMap(productEntity.getBarcodeMap());
        productDto.setExtraInfoMap(productEntity.getExtraInfoMap());
        productDto.setNutritionFact(nutritionFactConverter.getDto(productEntity.getNutritionFact()));
        productDto.setCatId(productEntity.getCatId());
        productDto.setDescription(productEntity.getDescription());
        productDto.setExpirationDurationDate(productEntity.getExpirationDurationDate());
        productDto.setGmoFree(productEntity.getGmoFree());
        productDto.setImageUrl(productEntity.getImageUrl());
        productDto.setImporter(productEntity.getImporter());
        productDto.setName(productEntity.getName());
        productDto.setOrganic(productEntity.getOrganic());
        productDto.setPackingType(productEntity.getPackingType());
        productDto.setPrice(productEntity.getPrice());
        productDto.setQuantityValue(productEntity.getQuantity().getValue());
        productDto.setQuantityUnit(productEntity.getQuantity().getUnit());
        return productDto;
    }

    public List<ProductDto> getDtoList(List<ProductEntity> productEntityList) {
        if (productEntityList == null)
            return null;
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        for (ProductEntity productEntity : productEntityList) {
            productDtoList.add(getDto(productEntity));
        }
        return productDtoList;
    }

}
