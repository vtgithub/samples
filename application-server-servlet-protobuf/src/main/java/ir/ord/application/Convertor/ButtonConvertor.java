package ir.ord.application.Convertor;

import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.entities.ButtonEntity;
import ir.ord.application.dto.ButtonDto;
import ir.ord.application.dto.protoes.ButtonProto;
import ir.ord.application.dto.protoes.StatusObjectProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/13/17.
 */
@ApplicationScoped
public class ButtonConvertor {

    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @Inject
    private FunctionalityObjectConvertor functionalityObjectConvertor;


    public ButtonProto.OrdButton.Builder getBuilderFromEntity(ButtonEntity buttonEntity) throws DaoException {
        if (buttonEntity == null)
            return null;
        ButtonDto buttonDto = this.getDto(buttonEntity);
        ButtonProto.OrdButton.Builder buttonBuilder = ButtonProto.OrdButton.newBuilder();
//        buttonBuilder.setId(buttonEntity.get_id());
        buttonBuilder.setName(buttonDto.getName());
//        buttonBuilder.setAccountId(buttonEntity.getAccountId());
        buttonBuilder.setDeviceToken(buttonDto.getDeviceToken());
        buttonBuilder.setNumOfFuncs(buttonDto.getNumOfFuncs());
        buttonBuilder.setAddressId(buttonDto.getAddressId());
        List<StatusObjectProto.StatusObject> statusObjectList = statusObjectConvertor.getProtoListFromEntityList(buttonDto.getStateList());
        if (statusObjectList != null)
            buttonBuilder.addAllStateList(statusObjectList);
        List<ButtonProto.ButtonFunctionalityObject> buttonFunctionalityObjectList = functionalityObjectConvertor.getProtoListFromDtoList(buttonDto.getFunctionalityObjectDtoList());
        if (buttonFunctionalityObjectList != null)
            buttonBuilder.addAllFunctionalityObjectDtoList(buttonFunctionalityObjectList);
        return buttonBuilder;
    }

    public ButtonDto getDto(ButtonEntity buttonEntity) throws DaoException {
        ButtonDto buttonDto = new ButtonDto();
//        buttonDto.set_id(buttonEntity.get_id());
//        buttonDto.setAccountId(buttonEntity.getAccountId());
//        buttonDto.setDeviceIp(buttonEntity.getDeviceIp());
        buttonDto.setName(buttonEntity.getName());
        buttonDto.setAddressId(buttonEntity.getAddressId());
        buttonDto.setDeviceToken(buttonEntity.getDeviceToken());
        buttonDto.setFunctionalityObjectDtoList(
                functionalityObjectConvertor.getDtoList(buttonEntity.getFunctionalityObjectList())
        );
        buttonDto.setNumOfFuncs(buttonEntity.getNumOfFuncs());
        buttonDto.setStateList(statusObjectConvertor.getDtoList(buttonEntity.getStateList()));
        return buttonDto;
    }

    public ButtonDto getDtoFromBuilder(ButtonProto.OrdButton.Builder buttonBuilder){
        ButtonDto buttonDto = new ButtonDto();
        buttonDto.setAddressId(buttonBuilder.getAddressId());
        buttonDto.set_id(buttonBuilder.getId());
        buttonDto.setNumOfFuncs((byte) buttonBuilder.getNumOfFuncs());
        buttonDto.setName(buttonBuilder.getName());
        buttonDto.setAccountId(buttonBuilder.getAccountId());
        buttonDto.setDeviceToken(buttonBuilder.getDeviceToken());
        buttonDto.setStateList(
                statusObjectConvertor.getDtoListFromBuilderList(buttonBuilder.getStateListBuilderList())
        );
        buttonDto.setFunctionalityObjectDtoList(
                functionalityObjectConvertor.getDtoListFromBuilderList(
                        buttonBuilder.getFunctionalityObjectDtoListBuilderList()
                )
        );
        return buttonDto;
    }

    public List<ButtonDto> getDtoList(List<ButtonEntity> buttonEntityList) throws DaoException {
        if (buttonEntityList == null)
            return null;
        List<ButtonDto> buttonDtoList = new ArrayList<ButtonDto>();
        for (ButtonEntity buttonEntity : buttonEntityList) {
            buttonDtoList.add(getDto(buttonEntity));
        }
        return buttonDtoList;
    }


    public List<ButtonProto.OrdButton.Builder> getBuilderListFromEntityList(List<ButtonEntity> buttonEntityList) throws DaoException {
        if (buttonEntityList == null || buttonEntityList.size() == 0)
            return null;
        List<ButtonProto.OrdButton.Builder> builderList = new ArrayList<ButtonProto.OrdButton.Builder>();
        for (ButtonEntity buttonEntity : buttonEntityList) {
            builderList.add(
                    this.getBuilderFromEntity(buttonEntity)
            );
        }
        return builderList;
    }

    public List<ButtonProto.OrdButton> getProtolistFromBuilderList(List<ButtonProto.OrdButton.Builder> listOfBuilder) {
        if (listOfBuilder == null)
            return null;
        List<ButtonProto.OrdButton> ordButtonList = new ArrayList<ButtonProto.OrdButton>();
        for (ButtonProto.OrdButton.Builder builder : listOfBuilder) {
            ordButtonList.add(builder.build());
        }
        return ordButtonList;
    }
}
