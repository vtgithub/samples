package ir.ord.application.Convertor;

import ir.ord.application.CommodityState;
import ir.ord.application.dal.entities.StatusObject;
import ir.ord.application.dto.StatusObjectDto;
import ir.ord.application.dto.protoes.StatusObjectProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class StatusObjectConvertor {

    StatusObjectDto getDto(StatusObject statusObject){
        if (statusObject == null)
            return null;

        StatusObjectDto statusObjectDto = new StatusObjectDto();
        statusObjectDto.setState(statusObject.getState());
        statusObjectDto.setDescription(statusObject.getDescription());
        statusObjectDto.setActor(statusObject.getActor());
        statusObjectDto.setTimeStamp(statusObject.getTimeStamp());
        return statusObjectDto;
    }


    private StatusObjectDto getDtoFromBuilder(StatusObjectProto.StatusObject.Builder statusObjectBuilder) {
        if (statusObjectBuilder == null)
            return null;
        StatusObjectDto statusObjectDto = new StatusObjectDto();
        statusObjectDto.setTimeStamp(statusObjectBuilder.getTimeStamp());
        statusObjectDto.setActor(statusObjectBuilder.getActor());
        statusObjectDto.setState(statusObjectBuilder.getState());
        statusObjectDto.setDescription(statusObjectBuilder.getDescription());
        return statusObjectDto;
    }

    public List<StatusObjectDto> getDtoList(List<StatusObject> stateList) {
        if (stateList == null || (stateList.size() == 0) )
            return null;
        List<StatusObjectDto> statusObjectDtoList = new ArrayList<StatusObjectDto>();
        for (StatusObject statusObject : stateList) {
            statusObject.setState(statusObject.getState());
            statusObjectDtoList.add(getDto(statusObject));
        }

        return statusObjectDtoList;
    }
    public List<StatusObjectDto> getDtoListForOrder(List<StatusObject> stateList) {
        if (stateList == null || (stateList.size() == 0) )
            return null;
        List<StatusObjectDto> statusObjectDtoList = new ArrayList<StatusObjectDto>();
        for (StatusObject statusObject : stateList) {
            if(isUserReadableState(statusObject.getState()))
                statusObjectDtoList.add(getDto(statusObject));
        }

        return statusObjectDtoList;
    }

    public List<StatusObjectDto> getDtoListFromBuilderList(
            List<StatusObjectProto.StatusObject.Builder> stateListBuilderList) {
        if (stateListBuilderList == null)
            return null;
        List<StatusObjectDto> statusObjectDtoList = new ArrayList<StatusObjectDto>();
        for (StatusObjectProto.StatusObject.Builder builder : stateListBuilderList) {
            statusObjectDtoList.add(getDtoFromBuilder(builder));
        }
        return statusObjectDtoList;
    }

    public List<StatusObjectProto.StatusObject> getProtoListFromEntityList(List<StatusObjectDto> stateList) {
        if (stateList == null)
            return null;
        List<StatusObjectProto.StatusObject> statusObjectList = new ArrayList<StatusObjectProto.StatusObject>();

        for (StatusObjectDto statusObjectDto : stateList) {
            StatusObjectProto.StatusObject.Builder statusObjectBuilder = getBuilderFromEntity(statusObjectDto);
            if (statusObjectBuilder!=null)
                statusObjectList.add(statusObjectBuilder.build());
        }
        return statusObjectList;
    }

    private StatusObjectProto.StatusObject.Builder getBuilderFromEntity(StatusObjectDto statusObjectDto) {
        if (statusObjectDto == null)
            return null;
        StatusObjectProto.StatusObject.Builder statusObjectBuilder = StatusObjectProto.StatusObject.newBuilder();
        if (statusObjectDto.getActor() != null)
            statusObjectBuilder.setActor(statusObjectDto.getActor());
        statusObjectBuilder.setTimeStamp(statusObjectDto.getTimeStamp());
        statusObjectBuilder.setState(statusObjectDto.getState());
        if (statusObjectDto.getDescription() != null)
            statusObjectBuilder.setDescription(statusObjectDto.getDescription());
        return statusObjectBuilder;
    }

    public StatusObject getCurrentStatusObject(List<StatusObject> statusObjectList){
        if (statusObjectList == null || statusObjectList.size()==0)
            return null;
        return statusObjectList.get(statusObjectList.size() - 1);
    }

    public StatusObjectDto getCurrentStatusObjectDto(List<StatusObjectDto> statusObjectDtoList){
        if (statusObjectDtoList == null || statusObjectDtoList.size()==0)
            return null;
        return statusObjectDtoList.get(statusObjectDtoList.size() - 1);
    }

    private boolean isUserReadableState(Integer state) {
        return !(
                state == CommodityState.USER_CONFIRMED.getCode() ||
                state == CommodityState.WAREHOUSE_PENDING.getCode() ||
                state == CommodityState.WAREHOUSE_CONFIRMED.getCode()
        );
    }

    public StatusObjectDto getCurrentUserReadableStatusObjectDto(List<StatusObject> statusObjectList){
        if (statusObjectList == null || statusObjectList.size() ==0)
            return null;
        for(int i = statusObjectList.size()-1; i >= 0; i--){
            if (isUserReadableState(statusObjectList.get(i).getState()))
                return getDto(statusObjectList.get(i));
        }
        return null;
    }
}
