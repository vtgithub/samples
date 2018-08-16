package ir.ord.application.Convertor.sources_convertors;

import ir.ord.application.Convertor.ButtonConvertor;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.entities.source_entities.ButtonSourceEntity;
import ir.ord.application.dto.source_dtos.ButtonSourceDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 7/29/17.
 */
@ApplicationScoped
public class ButtonSourceConvertor {

    @Inject
    private ButtonConvertor buttonConvertor;
//    public ButtonSourceEntity getEntity(ButtonSourceDto buttonSourceDto){
//        if (buttonSourceDto == null)
//            return null;
//        ButtonSourceEntity buttonSourceEntity = new ButtonSourceEntity();
//        buttonSourceEntity.setIp(buttonSourceDto.getIp());
//        buttonSourceEntity.setButtonVersion(buttonSourceDto.getButtonVersion());
//        buttonSourceEntity.setFuncKey(buttonSourceDto.getFuncKey());
//        buttonSourceEntity.setOrdButton(buttonConvertor.ge(buttonSourceDto.getOrdButton()));
//    }

    public ButtonSourceDto getDto(ButtonSourceEntity buttonSourceEntity) throws DaoException {
        ButtonSourceDto buttonSourceDto = new ButtonSourceDto();
        buttonSourceDto.setFuncKey(buttonSourceEntity.getFuncKey());
        buttonSourceDto.setButtonVersion(buttonSourceEntity.getButtonVersion());
        buttonSourceDto.setIp(buttonSourceEntity.getIp());
        buttonSourceDto.setOrdButton(buttonConvertor.getDto(buttonSourceEntity.getOrdButton()));
        return buttonSourceDto;
    }
}
