package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.TimePeriodEntity;
import ir.ord.application.dto.TimePeriodDto;
import ir.ord.application.dto.protoes.TimePeriodProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
@ApplicationScoped
public class TimePeriodConvertor {
    
    public TimePeriodDto getDto(TimePeriodEntity timePeriodEntity){
        if (timePeriodEntity == null)
            return null;
        TimePeriodDto timePeriodDto = new TimePeriodDto();
        timePeriodDto.setId(timePeriodEntity.get_id());
        timePeriodDto.setFromTime(timePeriodEntity.getFromTime());
        timePeriodDto.setToTime(timePeriodEntity.getToTime());
        timePeriodDto.setWeekDay(timePeriodEntity.getWeekDay());
        return timePeriodDto;
    }
    
    public TimePeriodEntity getEntity(TimePeriodDto timePeriodDto){
        if (timePeriodDto == null)
            return null;
        TimePeriodEntity timePeriodEntity = new TimePeriodEntity();
        if (timePeriodDto.getId()!=null)
            timePeriodEntity.set_id(timePeriodDto.getId());
        timePeriodEntity.setFromTime(timePeriodDto.getFromTime());
        timePeriodEntity.setToTime(timePeriodDto.getToTime());
        timePeriodEntity.setWeekDay(timePeriodDto.getWeekDay());
        return timePeriodEntity;
    }

    public TimePeriodEntity getEntity(TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder){
        if (timePeriodRequestBuilder == null)
            return null;
        TimePeriodEntity timePeriodEntity = new TimePeriodEntity();
        if (timePeriodRequestBuilder.getId()!=null && !timePeriodRequestBuilder.getId().equals(""))
            timePeriodEntity.set_id(timePeriodRequestBuilder.getId());
        timePeriodEntity.setFromTime(timePeriodRequestBuilder.getFromTime());
        timePeriodEntity.setToTime(timePeriodRequestBuilder.getToTime());
        timePeriodEntity.setWeekDay(timePeriodRequestBuilder.getWeekDay());
        return timePeriodEntity;
    }
    public TimePeriodDto getDtoFromBuilder(TimePeriodProto.TimePeriodRequest.Builder builder){
        if (builder == null)
            return null;
        TimePeriodDto timePeriodDto = new TimePeriodDto();
        timePeriodDto.setWeekDay(builder.getWeekDay());
        timePeriodDto.setToTime(builder.getToTime());
        timePeriodDto.setFromTime(builder.getFromTime());
        if (builder.getId() != null)
            timePeriodDto.setId(builder.getId());
        return timePeriodDto;
    }

    public List<TimePeriodDto> getTimePeriodDtoListFromBuilderList(
            List<TimePeriodProto.TimePeriodRequest.Builder> timePeriodBuilderList) {
        if (timePeriodBuilderList == null)
            return null;
        List<TimePeriodDto> timePeriodDtoList = new ArrayList<TimePeriodDto>();
        for (TimePeriodProto.TimePeriodRequest.Builder builder : timePeriodBuilderList) {
            timePeriodDtoList.add(getDtoFromBuilder(builder));
        }
        return timePeriodDtoList;
    }

    public TimePeriodProto.TimePeriodRequest.Builder getTimePeriodRequestFromTimePeriod(
            TimePeriodProto.TimePeriod.Builder builder) {
        TimePeriodProto.TimePeriodRequest.Builder requestBuilder = TimePeriodProto.TimePeriodRequest.newBuilder();
        requestBuilder.setFromTime(builder.getFromTime());
        requestBuilder.setToTime(builder.getToTime());
        requestBuilder.setWeekDay(builder.getWeekDay());
        return requestBuilder;
    }
    // -- helpers

    public List<TimePeriodDto> getTimePeriodDtoList(List<TimePeriodEntity> timePeriodEntityList) {
        if (timePeriodEntityList == null)
            return null;
        List<TimePeriodDto> timePeriodDtoList = new ArrayList<TimePeriodDto>();
        if (timePeriodDtoList != null){
            for (TimePeriodEntity timePeriodEntity: timePeriodEntityList){
                timePeriodDtoList.add(getDto(timePeriodEntity));
            }
        }
        return timePeriodDtoList;
    }

    public List<TimePeriodEntity> getTimePeriodEntityList(List<TimePeriodDto> timePeriodDtoList) {
        if (timePeriodDtoList == null)
            return null;
        List<TimePeriodEntity> timePeriodEntityList = new ArrayList<TimePeriodEntity>();
        if(timePeriodDtoList != null){
            for(TimePeriodDto timePeriodDto : timePeriodDtoList){
                timePeriodEntityList.add(getEntity(timePeriodDto));
            }
        }
        return timePeriodEntityList;
    }


}
