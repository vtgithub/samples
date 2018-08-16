package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.TimePeriodObject;
import ir.ord.application.dto.TimePeriodObjectDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class TimePeriodObjectConvertor {

    @Inject
    private TimePeriodConvertor timePeriodConvertor;

    TimePeriodObjectDto getDto(TimePeriodObject timePeriodObject){
        if (timePeriodObject == null)
            return null;

        TimePeriodObjectDto timePeriodObjectDto = new TimePeriodObjectDto();
        timePeriodObjectDto.setTimeStamp(timePeriodObject.getTimeStamp());
        timePeriodObjectDto.setActor(timePeriodObject.getActor());
        timePeriodObjectDto.setDate(timePeriodObject.getDate());
        timePeriodObjectDto.setDescription(timePeriodObject.getDescription());
        timePeriodObjectDto.setTimePeriodDto(timePeriodConvertor.getDto(timePeriodObject.getTimePeriodEntity()));
        return timePeriodObjectDto;

    }

//    public TimePeriodObjectDto getDtoFromBuilder(TimePeriodObjectProto.TimePeriodObject.Builder builder){
//        if (builder == null)
//            return null;
//
//        TimePeriodObjectDto timePeriodObjectDto = new TimePeriodObjectDto();
//        timePeriodObjectDto.setTimePeriodDto(timePeriodConvertor.getDtoFromBuilder(builder.getTimePeriodDtoBuilder()));
//        timePeriodObjectDto.setDescription(builder.getDescription());
//        timePeriodObjectDto.setDate(builder.getDate());
//        timePeriodObjectDto.setActor(builder.getActor());
//        timePeriodObjectDto.setTimeStamp(builder.getTimeStamp());
//        return timePeriodObjectDto;
//    }

//    public List<TimePeriodObjectDto> getDtoListFromBuilderList(
//            List<TimePeriodObjectProto.TimePeriodObject.Builder> timePeriodObjectDtoListBuilderList) {
//        if (timePeriodObjectDtoListBuilderList == null)
//            return null;
//        List<TimePeriodObjectDto> timePeriodObjectDtoList = new ArrayList<TimePeriodObjectDto>();
//        for (TimePeriodObjectProto.TimePeriodObject.Builder builder : timePeriodObjectDtoListBuilderList) {
//            timePeriodObjectDtoList.add(getDtoFromBuilder(builder));
//        }
//        return timePeriodObjectDtoList;
//    }

}
