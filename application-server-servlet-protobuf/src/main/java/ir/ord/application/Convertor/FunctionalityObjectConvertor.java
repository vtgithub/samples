package ir.ord.application.Convertor;

import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.PackageDao;
import ir.ord.application.dal.entities.Functionality;
import ir.ord.application.dal.entities.FunctionalityObject;
import ir.ord.application.dto.FunctionalityObjectDto;
import ir.ord.application.dto.PackageDto;
import ir.ord.application.dto.protoes.ButtonProto;
import ir.ord.application.dal.entities.PackageEntity;
import ir.ord.application.dto.protoes.PackageProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/30/17.
 */
@ApplicationScoped
public class FunctionalityObjectConvertor {
    @Inject
    private PackageConvertor packageConvertor;
    @Inject
    private PackageDao packageDao;
    @Inject
    private FunctionalityConverter functionalityConverter;

    public FunctionalityObjectDto getDto(FunctionalityObject functionalityObject) throws DaoException {
        FunctionalityObjectDto functionalityObjectDto = new FunctionalityObjectDto();
        functionalityObjectDto.setCatId(functionalityObject.getCatId());

        functionalityObjectDto.setFuncNumber(functionalityObject.getFuncNumber());
        PackageEntity packageEntity = packageDao.getById(
                getPackageIdFromLastFunctionality(functionalityObject.getFunctionalityList())
        );
        PackageDto packageDto = packageConvertor.getDto(packageEntity);
        functionalityObjectDto.setPackageDto(packageDto);
        return functionalityObjectDto;
    }

    private FunctionalityObjectDto getDtoFromBuilder(ButtonProto.ButtonFunctionalityObject.Builder buttonFunctionalityObjetBuilder) {
        if(buttonFunctionalityObjetBuilder == null)
            return null;
        FunctionalityObjectDto functionalityObjectDto = new FunctionalityObjectDto();
        functionalityObjectDto.setCatId(buttonFunctionalityObjetBuilder.getCatId());
        functionalityObjectDto.setCatName(buttonFunctionalityObjetBuilder.getCatName());
        functionalityObjectDto.setFuncNumber(buttonFunctionalityObjetBuilder.getFuncNumber());
        functionalityObjectDto.setPackageDto(
                packageConvertor.getDtoFromRowBuilder(
                        buttonFunctionalityObjetBuilder.getPackageDtoBuilder()
                )
        );
        return functionalityObjectDto;
    }


    private ButtonProto.ButtonFunctionalityObject.Builder getBuilderFromDto(FunctionalityObjectDto functionalityObjectDto) {
        if (functionalityObjectDto == null)
            return null;
        ButtonProto.ButtonFunctionalityObject.Builder builder = ButtonProto.ButtonFunctionalityObject.newBuilder();
        builder.setCatId(functionalityObjectDto.getCatId());
        if (functionalityObjectDto.getCatName()!=null)
            builder.setCatName(functionalityObjectDto.getCatName());
        builder.setFuncNumber(functionalityObjectDto.getFuncNumber());
        PackageProto.Package.Builder packageBuilder = packageConvertor.getBuilderFromDto(functionalityObjectDto.getPackageDto());
        if (packageBuilder != null)
            builder.setPackageDto(packageBuilder.build());

        return builder;
    }

    // --------- helper
    private String getPackageIdFromLastFunctionality(List<Functionality> functionalityList) {
        if (functionalityList == null || functionalityList.size() == 0)
            return null;
        return functionalityList.get(functionalityList.size()-1).getPackageId();
    }

    public List<FunctionalityObjectDto> getDtoListFromBuilderList(
            List<ButtonProto.ButtonFunctionalityObject.Builder> buttonFunctionalityObjectDtoListBuilderList) {
        if (buttonFunctionalityObjectDtoListBuilderList == null)
            return null;
        List<FunctionalityObjectDto> functionalityObjectDtoList = new ArrayList<FunctionalityObjectDto>();
        for (ButtonProto.ButtonFunctionalityObject.Builder functionalityObjetBuilder : buttonFunctionalityObjectDtoListBuilderList) {
            functionalityObjectDtoList.add(getDtoFromBuilder(functionalityObjetBuilder));
        }
        return functionalityObjectDtoList;
    }

    public List<ButtonProto.ButtonFunctionalityObject> getProtoListFromDtoList(
            List<FunctionalityObjectDto> functionalityObjectDtoList){
        if (functionalityObjectDtoList == null)
            return null;
        List<ButtonProto.ButtonFunctionalityObject> buttonFunctionalityObjectList
                = new ArrayList<ButtonProto.ButtonFunctionalityObject>();
        for (FunctionalityObjectDto functionalityObjectDto : functionalityObjectDtoList) {
            ButtonProto.ButtonFunctionalityObject.Builder buttonFunctionalityObjectBuilder = getBuilderFromDto(functionalityObjectDto);
            if (buttonFunctionalityObjectBuilder!=null)
                buttonFunctionalityObjectList.add(buttonFunctionalityObjectBuilder.build());
        }
        return buttonFunctionalityObjectList;
    }


    public List<FunctionalityObjectDto> getDtoList(List<FunctionalityObject> functionalityObjectList) throws DaoException {
        if (functionalityObjectList == null)
            return null;
        List<FunctionalityObjectDto> functionalityObjectDtoList = new ArrayList<FunctionalityObjectDto>();
        for (FunctionalityObject functionalityObject: functionalityObjectList){
            functionalityObjectDtoList.add(getDto(functionalityObject));
        }
        return functionalityObjectDtoList;
    }



}
