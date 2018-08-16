package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.OptionObject;
import ir.ord.application.dto.ComboElementDto;
import ir.ord.application.dto.protoes.ParameterProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 10/3/17.
 */
@ApplicationScoped
public class OptionObjectConverter {

    public ComboElementDto getDto(OptionObject optionObject){
        if (optionObject == null)
            return null;
        ComboElementDto comboElementDto = new ComboElementDto();
        comboElementDto.setVal(optionObject.getVal());
        comboElementDto.setId(optionObject.getId());
        return comboElementDto;
    }

    public OptionObject getEntity(ComboElementDto comboElementDto){
        if (comboElementDto == null)
            return null;
        OptionObject optionObject = new OptionObject();
        optionObject.setId(comboElementDto.getId());
        optionObject.setVal(comboElementDto.getVal());
        return optionObject;
    }


    public List<ComboElementDto> getDtoList(List<OptionObject> optionObjectList) {
        if (optionObjectList == null || optionObjectList.size()==0)
            return null;
        List<ComboElementDto> comboElementDtoList = new ArrayList<ComboElementDto>();
        for (OptionObject optionObject : optionObjectList) {
            comboElementDtoList.add(getDto(optionObject));
        }

        return comboElementDtoList;
    }

    public List<OptionObject> getEntityList(List<ComboElementDto> comboElementList) {
        if (comboElementList == null || comboElementList.size() == 0)
            return null;
        List<OptionObject> optionObjectList = new ArrayList<OptionObject>();
        for (ComboElementDto comboElementDto : comboElementList) {
            optionObjectList.add(getEntity(comboElementDto));
        }

        return optionObjectList;

    }

    public static List<ComboElementDto> getDtoFromBuilderList(List<ParameterProto.ComboElement.Builder> comboElementBuilderList) {
        if (comboElementBuilderList == null)
            return null;
        List<ComboElementDto> comboElementDtoList = new ArrayList<ComboElementDto>();
        for (ParameterProto.ComboElement.Builder builder : comboElementBuilderList) {
            comboElementDtoList.add(getDtoFromBuilder(builder));
        }
        return comboElementDtoList;
    }

    private static ComboElementDto getDtoFromBuilder(ParameterProto.ComboElement.Builder builder) {
        if (builder == null)
            return null;
        ComboElementDto comboElementDto = new ComboElementDto();
        comboElementDto.setId(builder.getId());
        comboElementDto.setVal(builder.getVal());
        return comboElementDto;
    }


}
