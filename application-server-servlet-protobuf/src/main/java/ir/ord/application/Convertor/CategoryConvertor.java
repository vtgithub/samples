package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.CategoryEntity;
import ir.ord.application.dto.CategoryDto;
import ir.ord.application.dto.protoes.CategoryProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@ApplicationScoped
public class CategoryConvertor {

    public CategoryEntity toEntity(CategoryProto.CategoryRequest.Builder categoryRequestBuilder){
        if (categoryRequestBuilder == null)
            return null;
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryRequestBuilder.getId() != null && !categoryRequestBuilder.getId().equals(""))
            categoryEntity.set_id(categoryRequestBuilder.getId());
        categoryEntity.setName(categoryRequestBuilder.getName());
        if (categoryRequestBuilder.getImageUrl()!=null && !categoryRequestBuilder.getImageUrl().equals(""))
            categoryEntity.setImageUrl(categoryRequestBuilder.getImageUrl());
        List<CategoryEntity>  categoryEntityList = null;
        if ((categoryRequestBuilder.getChildrenBuilderList() != null) && (categoryRequestBuilder.getChildrenBuilderList().size()!=0)){
            categoryEntityList = new ArrayList<CategoryEntity>();
            for (CategoryProto.CategoryRequest.Builder categoryRequestBuilderChild: categoryRequestBuilder.getChildrenBuilderList()){
                if (categoryRequestBuilderChild != null && !categoryRequestBuilderChild.getName().equals(""))
                    categoryEntityList.add(toEntity(categoryRequestBuilderChild));
            }
        }
        categoryEntity.setChildren(categoryEntityList);
        return categoryEntity;
    }

    public CategoryProto.Category.Builder toBuilder(CategoryEntity categoryEntity){
        if (categoryEntity == null)
            return null;
        CategoryProto.Category.Builder categoryBuilder= CategoryProto.Category.newBuilder();
        categoryBuilder.setId(categoryEntity.get_id());
        categoryBuilder.setImageUrl(categoryEntity.getImageUrl());
        categoryBuilder.setName(categoryEntity.getName());
        List<CategoryProto.Category> categoryBuilderList = null;
        if ((categoryEntity.getChildren() != null) && (categoryEntity.getChildren().size()!=0)) {
            categoryBuilderList = new ArrayList<CategoryProto.Category>();
            for (CategoryEntity categoryEntityChild : categoryEntity.getChildren())
                if (categoryEntityChild != null && !categoryEntityChild.getName().equals(""))
                    categoryBuilderList.add(toBuilder(categoryEntityChild).build());
        }
        categoryBuilder.addAllChildren(categoryBuilderList);
        return categoryBuilder;
    }

    public CategoryEntity toEntity(CategoryDto categoryDto){
        if (categoryDto == null)
            return null;
        CategoryEntity categoryEntity = new CategoryEntity();
        if (categoryDto.get_id() != null && !categoryDto.get_id().equals(""))
            categoryEntity.set_id(categoryDto.get_id());
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setImageUrl(categoryDto.getImageUrl());
        List<CategoryEntity>  categoryEntityList = null;
        if ((categoryDto.getChildren() != null) && (categoryDto.getChildren().size()!=0)){
            categoryEntityList = new ArrayList<CategoryEntity>();
            for (CategoryDto categoryDtoChild: categoryDto.getChildren()){
                if (categoryDtoChild != null && !categoryDtoChild.getName().equals(""))
                    categoryEntityList.add(toEntity(categoryDtoChild));
            }
        }
        categoryEntity.setChildren(categoryEntityList);
        return categoryEntity;
    }

    public CategoryDto toDto(CategoryEntity categoryEntity){
        if (categoryEntity == null)
            return null;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.set_id(categoryEntity.get_id());
        categoryDto.setImageUrl(categoryEntity.getImageUrl());
        categoryDto.setName(categoryEntity.getName());
        List<CategoryDto> categoryDtoList = null;
        if ((categoryEntity.getChildren() != null) && (categoryEntity.getChildren().size()!=0)) {
            categoryDtoList = new ArrayList<CategoryDto>();
            for (CategoryEntity categoryEntityChild : categoryEntity.getChildren())
                if (categoryEntityChild != null && !categoryEntityChild.getName().equals(""))
                    categoryDtoList.add(toDto(categoryEntityChild));
        }
        categoryDto.setChildren(categoryDtoList);
        return categoryDto;
    }

    public CategoryDto getDtoFromBuilder(CategoryProto.CategoryRequest.Builder catBuilder){
        if (catBuilder == null)
            return null;
        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.set_id(catBuilder.getId());
        categoryDto.setName(catBuilder.getName());
//        categoryDto.setImage(catBuilder.getImage().toByteArray());
        categoryDto.setImageUrl(catBuilder.getImageUrl());
        List<CategoryDto> categoryDtoList = null;
        if ( (catBuilder.getChildrenBuilderList() != null) && (catBuilder.getChildrenBuilderList().size() != 0) ){
            categoryDtoList = new ArrayList<CategoryDto>();
            for (CategoryProto.CategoryRequest.Builder childBuilder : catBuilder.getChildrenBuilderList()) {
                if (childBuilder != null && !childBuilder.getName().equals(""))
                    categoryDtoList.add(getDtoFromBuilder(childBuilder));
            }
        }
        categoryDto.setChildren(categoryDtoList);
        return categoryDto;
    }


    public List<CategoryDto> getCategoryDtoList(List<CategoryEntity> categoryEntityList) {
        if(categoryEntityList == null || categoryEntityList.size() == 0 )
            return null;
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();

        for (CategoryEntity categoryEntity: categoryEntityList){
            categoryDtoList.add(toDto(categoryEntity));
        }

        return categoryDtoList;
    }
}
