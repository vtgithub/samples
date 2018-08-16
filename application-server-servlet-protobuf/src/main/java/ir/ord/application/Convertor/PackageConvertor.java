package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.PackageEntity;
import ir.ord.application.dto.PackageDto;
import ir.ord.application.dto.protoes.PackageProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/2/17.
 */
@ApplicationScoped
public class PackageConvertor {


    public PackageDto getDto(PackageEntity packageEntity){
        if (packageEntity == null)
            return null;
        PackageDto packageDto = new PackageDto();
        if(packageEntity.getProducts()!=null && packageEntity.getProducts().size()!=0)
            packageDto.setProducts(packageEntity.getProducts());
//        packageDto.setCategoryId(packageEntity.getCategoryId());
        packageDto.setDescription(packageEntity.getDescription());
        packageDto.setId(packageEntity.get_id());
//        packageDto.setImageUrl(packageEntity.getImageUrl());
        packageDto.setName(packageEntity.getName());
        packageDto.setPrice(packageEntity.getPrice());
        return packageDto;
    }

    public PackageEntity getEntity(PackageDto packageDto){
        if (packageDto == null)
            return null;
        PackageEntity packageEntity = new PackageEntity();
//        packageEntity.setCategoryId(packageDto.getCategoryId());
        packageEntity.setDescription(packageDto.getDescription());
//        packageEntity.set_id(packageDto.getId());
//        packageEntity.setImageUrl(packageDto.getImageUrl());
        packageEntity.setName(packageDto.getName());
        packageEntity.setPrice(packageDto.getPrice());
        if (packageDto.getProducts() != null && packageDto.getProducts().size() != 0)
            packageEntity.setProducts(packageDto.getProducts());
        return packageEntity;
    }


    public PackageEntity getEntity(PackageProto.PackageRequest.Builder packageBuilder){
        if (packageBuilder == null)
            return null;
        PackageEntity packageEntity = new PackageEntity();
//        packageEntity.setCategoryId(packageBuilder.getCategoryId());
        if (packageBuilder.getDescription() != null && !packageBuilder.getDescription().equals(""))
            packageEntity.setDescription(packageBuilder.getDescription());
//        packageEntity.set_id(packageBuilder.getId());
//        if (packageBuilder.getImageUrl() != null && !packageBuilder.getImageUrl().equals(""))
//            packageEntity.setImageUrl(packageBuilder.getImageUrl());
        packageEntity.setName(packageBuilder.getName());
        packageEntity.setPrice(packageBuilder.getPrice());
        return packageEntity;
    }

    public PackageDto getDtoFromBuilder(PackageProto.PackageRequest.Builder builder){
        if (builder == null)
            return null;
        PackageDto packageDto = new PackageDto();
        packageDto.setCategoryId(builder.getCategoryId());
        packageDto.setName(builder.getName());
//        packageDto.setImageUrl(builder.getImageUrl());
//        packageDto.setId(builder.getId());
        packageDto.setPrice(builder.getPrice());
        packageDto.setDescription(builder.getDescription());
        return packageDto;
    }

    public PackageDto getDtoFromRowBuilder(PackageProto.Package.Builder builder) {
        if (builder == null)
            return null;
        PackageDto packageDto = new PackageDto();
        packageDto.setCategoryId(builder.getCategoryId());
        packageDto.setName(builder.getName());
//        packageDto.setImageUrl(builder.getImageUrl());
        packageDto.setId(builder.getId());
        packageDto.setPrice(builder.getPrice());
        packageDto.setDescription(builder.getDescription());
        return packageDto;
    }

    public PackageProto.Package.Builder getBuilderFromDto(PackageDto packageDto) {
        if (packageDto == null)
            return null;
        PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
        builder.setCategoryId(packageDto.getCategoryId());
        builder.setName(packageDto.getName());
//        if (packageDto.getImageUrl() != null)
//            builder.setImageUrl(packageDto.getImageUrl());
        builder.setPrice(packageDto.getPrice());
        if (packageDto.getDescription() != null)
            builder.setDescription(packageDto.getDescription());
        return builder;
    }


    public List<PackageDto> getPackageDtoList(List<PackageEntity> packageEntityList) {
        if (packageEntityList == null)
            return null;
        List<PackageDto> packageDtoList = new ArrayList<PackageDto>();
        for (PackageEntity packageEntity : packageEntityList){
            packageDtoList.add(getDto(packageEntity));
        }
        return packageDtoList;
    }
}
